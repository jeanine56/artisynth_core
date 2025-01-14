package artisynth.core.materials;

import java.io.*;
import java.util.*;

import maspack.interpolation.CubicHermiteSpline1d;
import maspack.interpolation.CubicHermiteSpline1d.Knot;

import artisynth.core.util.ScalableUnits;
import artisynth.core.util.ScanToken;
import artisynth.core.modelbase.*;
import maspack.properties.*;
import maspack.util.*;
import maspack.matrix.*;

/**
 * Base class for point-to-point muscle materials that contains optional
 * attributes for defining force-length curves.
 */
public abstract class AxialMuscleMaterialBase extends AxialMaterial {

   CubicHermiteSpline1d myActiveForceLengthCurve;
   CubicHermiteSpline1d myPassiveForceLengthCurve;
   CubicHermiteSpline1d myTendonForceLengthCurve;
   CubicHermiteSpline1d myForceVelocityCurve;

   /**
    * Queries the active force length curve for this material.  Returns {@code
    * null} if this curve has not been set.
    *
    * @return current active force length curve
    */
   public CubicHermiteSpline1d getActiveForceLengthCurve() {
      return myActiveForceLengthCurve;
   }

   /**
    * Sets the active force length curve for this material, or removes it if
    * {@code curve} is set to {@code null}. Any specified curve is copied
    * internally.
    *
    * @param curve new active force length curve
    */
   public void setActiveForceLengthCurve (CubicHermiteSpline1d curve) {
      if (curve != null) {
         myActiveForceLengthCurve = new CubicHermiteSpline1d (curve);
      }
      else {
         myActiveForceLengthCurve = null;
      }
   }

   /**
    * Queries the passive force length curve for this material.  Returns {@code
    * null} if this curve has not been set.
    *
    * @return current passive force length curve
    */
   public CubicHermiteSpline1d getPassiveForceLengthCurve() {
      return myPassiveForceLengthCurve;
   }

   /**
    * Sets the passive force length curve for this material, or removes it if
    * {@code curve} is set to {@code null}. Any specified curve is copied
    * internally.
    *
    * @param curve new passive force length curve
    */
   public void setPassiveForceLengthCurve (CubicHermiteSpline1d curve) {
      if (curve != null) {
         myPassiveForceLengthCurve = new CubicHermiteSpline1d (curve);
      }
      else {
         myPassiveForceLengthCurve = null;
      }
   }

   /**
    * Queries the tendon force length curve for this material.  Returns {@code
    * null} if this curve has not been set.
    *
    * @return current tendon force length curve
    */
   public CubicHermiteSpline1d getTendonForceLengthCurve() {
      return myTendonForceLengthCurve;
   }

   /**
    * Sets the tendon force length curve for this material, or removes it if
    * {@code curve} is set to {@code null}. Any specified curve is copied
    * internally.
    *
    * @param curve new tendon force length curve
    */
   public void setTendonForceLengthCurve (CubicHermiteSpline1d curve) {
      if (curve != null) {
         myTendonForceLengthCurve = new CubicHermiteSpline1d (curve);
      }
      else {
         myTendonForceLengthCurve = null;
      }
   }

   /**
    * Queries the force velocity curve for this material.  Returns {@code
    * null} if this curve has not been set.
    *
    * @return current force velocity curve
    */
   public CubicHermiteSpline1d getForceVelocityCurve() {
      return myForceVelocityCurve;
   }

   /**
    * Sets the force velocity curve for this material, or removes it if
    * {@code curve} is set to {@code null}. Any specified curve is copied
    * internally.
    *
    * @param curve new force velocity curve
    */
   public void setForceVelocityCurve (CubicHermiteSpline1d curve) {
      if (curve != null) {
         myForceVelocityCurve = new CubicHermiteSpline1d (curve);
      }
      else {
         myForceVelocityCurve = null;
      }
   }

   public void writeItems (
      PrintWriter pw, NumberFormat fmt, CompositeComponent ancestor)
      throws IOException {
      super.writeItems (pw, fmt, ancestor);
      if (myActiveForceLengthCurve != null) {
         pw.print ("activeForceLengthCurve=");
         myActiveForceLengthCurve.write (pw, fmt, ancestor);
      }
      if (myPassiveForceLengthCurve != null) {
         pw.print ("passiveForceLengthCurve=");
         myPassiveForceLengthCurve.write (pw, fmt, ancestor);
      }
      if (myTendonForceLengthCurve != null) {
         pw.print ("tendonForceLengthCurve=");
         myTendonForceLengthCurve.write (pw, fmt, ancestor);
      }
      if (myForceVelocityCurve != null) {
         pw.print ("forceVelocityCurve=");
         myForceVelocityCurve.write (pw, fmt, ancestor);
      }
   }

   protected boolean scanItem (ReaderTokenizer rtok, Deque<ScanToken> tokens)
      throws IOException {
      // if keyword is a property name, try scanning that
      rtok.nextToken();
      if (ScanWriteUtils.scanAttributeName (
             rtok, "activeForceLengthCurve")) {
         myActiveForceLengthCurve = new CubicHermiteSpline1d();
         myActiveForceLengthCurve.scan (rtok, null);
         return true;
      }
      else if (ScanWriteUtils.scanAttributeName (
                  rtok, "passiveForceLengthCurve")) {
         myPassiveForceLengthCurve = new CubicHermiteSpline1d();
         myPassiveForceLengthCurve.scan (rtok, null);
         return true;
      }
      else if (ScanWriteUtils.scanAttributeName (
                  rtok, "tendonForceLengthCurve")) {
         myTendonForceLengthCurve = new CubicHermiteSpline1d();
         myTendonForceLengthCurve.scan (rtok, null);
         return true;
      }
      else if (ScanWriteUtils.scanAttributeName (
                  rtok, "forceVelocityCurve")) {
         myForceVelocityCurve = new CubicHermiteSpline1d();
         myForceVelocityCurve.scan (rtok, null);
         return true;
      }
      rtok.pushBack();
      return super.scanItem (rtok, tokens);
   }

   public static CubicHermiteSpline1d createActiveForceLengthCurve (
      double minActiveNormFiberLength,
      double transitionNormFiberLength,
      double maxActiveNormFiberLength,
      double shallowAscendingSlope,
      double minimumValue) {

      if (minActiveNormFiberLength <= 0) {
         throw new IllegalArgumentException (
            "minActiveNormFiberLength must be > 0");
      }
      if (transitionNormFiberLength >= 1) {
         throw new IllegalArgumentException (
            "transitionNormFiberLength must be < 1");
      }
      if (minActiveNormFiberLength >= transitionNormFiberLength) {
         throw new IllegalArgumentException (
            "minActiveNormFiberLength must be < transitionNormFiberLength");
      }
      if (maxActiveNormFiberLength <= 1) {
         throw new IllegalArgumentException (
            "maxActiveNormFiberLength must be > 1");
      }
      if (shallowAscendingSlope < 0) {
         throw new IllegalArgumentException (
            "shallowAscendingSlope must be >= 0");
      }
      if (shallowAscendingSlope >= 1/(1-transitionNormFiberLength)) {
         throw new IllegalArgumentException (
            "shallowAscendingSlope must be < 1/(1-transitionNormFiberLength)");
      }
      if (minimumValue < 0) {
         throw new IllegalArgumentException (
            "minimumValue must be >= 0");
      }
      
      CubicHermiteSpline1d curve = new CubicHermiteSpline1d();

      // start point
      curve.addKnot (minActiveNormFiberLength, minimumValue, 0.0);

      // trans point
      double xt = transitionNormFiberLength;
      curve.addKnot (
         xt, 1.0 - shallowAscendingSlope*(1.0-xt), shallowAscendingSlope);

      // max point
      curve.addKnot (1.0, 1.0, 0.0);

      // end point
      curve.addKnot (maxActiveNormFiberLength, minimumValue, 0);
      return curve;
   }

   /**
    * Check for an inflexion point within the curve interval specified by the
    * {@code idx}-th knot, and if one is found, try to remove it by adding an
    * additional knot the interval.
    */
   static void addAntiInflexionKnot (CubicHermiteSpline1d curve, int idx0) {

      Knot knot0 = curve.getKnot(idx0);
      Knot knot1 = curve.getKnot(idx0+1);

      // unpack the x, y and dy parameters at each knot. 
      double x0 = knot0.getX();
      double x1 = knot1.getX();
      double h = x1-x0; // length of the interval
      double y0 = knot0.getY();
      double dy0 = knot0.getDy();
      double y1 = knot1.getY();
      double dy1 = knot1.getDy();

      // upack the a3 and a2 cubic coefficients for knot0. These are related
      // the initial acceleration y''(0) and (constant) jerk value y'''(x) by
      //
      // y''(0) = 2 a2
      // y'''(x) = 6 a3
      //
      // Note: each knot's cubic and associated coefficients are defined with
      // respect to the shifted interval [0 x1-x0].
      double a2 = knot0.getA2();
      double a3 = knot0.getA3();

      // see if there is an inflexion point in the interval.  This will be true
      // if y''(x) has a zero. If it does not, there is no need to do anything.
      if (a3 != 0) {
         double chk = -a2/(3*a3);
         if (chk <= 0 && chk >= h) {
            // no inflexion
            return;
         }
      }
      if (dy1-dy0 == 0) {
         // can't remove inflexion if there is no end point change y'(x)
         return;
      }
      // Try to remove the inflexion by adding an extra knot at x = tau in the
      // interval [0, h], and replace the straight line defining y''(x) with a
      // piecewise linear version with curvature values c0, ctau and c1
      // defined at x = {0, tau, h}.
      if (Math.abs(dy0) > Math.abs(dy1)) {
         // forward direction: curvature at the beginning and set c1 = 0.
         // See if solutuin is feasible by solving for tau with ctau = 0.
         double tau = 3*(dy1*h-(y1-y0))/(dy1-dy0);
         if (tau <= 0 || tau >= h) {
            // can't do it - no solution
            return;
         }
         // If feasible, reduce tau by 2/3 to get a soother curve:
         tau = 2*tau/3;
         // Now backsolve for c0 and ctau:
         Vector2d cvec = new Vector2d();
         Vector2d b = new Vector2d (y1-y0+(dy1-dy0)*tau - h*dy1, dy1-dy0);
         Matrix2d M = new Matrix2d (
            tau*tau/3, (2*tau-h)*h/6, tau/2, h/2);
         double d = Matrix2d.solve (M, b, cvec);
         double c0 = cvec.x;
         double ctau = cvec.y;
         double ytau = y0 + dy0*tau + (c0/3 + ctau/6)*tau*tau;
         double dytau = dy0 + (c0 + ctau)*tau/2;
         curve.addKnot (x0+tau, ytau, dytau);
      }
      else {
         // other direction: apply curvature at interval end and set c0 = 0.
         double tau = 3*((y1-y0)-dy0*h)/(dy1-dy0);
         if (tau <= 0 || tau >= h) {
            // can't do it - no solution
            return;
         }
         // reduce tau by 2/3 to get a soother curve:
         tau = 2*tau/3;
         //  backsolve for c1 and ctau:
         Vector2d cvec = new Vector2d();
         Vector2d b = new Vector2d (y1-y0-(dy1-dy0)*tau - h*dy0, dy1-dy0);
         Matrix2d M = new Matrix2d (
            -tau*tau/3, -(2*tau-h)*h/6, tau/2, h/2);
         double d = Matrix2d.solve (M, b, cvec);
         double ctau = cvec.y;
         double ytau = y0 + dy0*(h-tau) + ctau*(h-tau)*(h-tau)/6;
         double dytau = dy0 + ctau*(h-tau)/2;
         curve.addKnot (x0+h-tau, ytau, dytau);
      }
   }

   public static CubicHermiteSpline1d createForceVelocityCurve (
      double concentricSlopeAtVmax,
      double concentricSlopeNearVmax,
      double isometricSlope,
      double eccentricSlopeAtVmax, 
      double eccentricSlopeNearVmax,
      double maxEccentricVelocityForceMultiplier,
      double concentricCurviness,
      double eccentricCurviness) {

      // check conditions
      if (concentricSlopeAtVmax < 0 || concentricSlopeAtVmax >= 1) {
         throw new IllegalArgumentException (
            "concentricSlopeAtVmax must be in the range [0,1)");
      }
      if (concentricSlopeNearVmax <= concentricSlopeAtVmax ||
          concentricSlopeNearVmax >= isometricSlope) {
         throw new IllegalArgumentException (
            "concentricSlopeNearVmax must be > concentricSlopeAtVmax "+
            "and < isometricSlope");
      }
      if (isometricSlope <= 1) {
         throw new IllegalArgumentException (
            "isometricSlope must be > 1");
      }
      if (eccentricSlopeNearVmax >= isometricSlope ||
          eccentricSlopeNearVmax <= eccentricSlopeAtVmax) {
         throw new IllegalArgumentException (
            "eccentricSlopeNearVmax must be < isometricSlope "+
            "and > eccentricSlopeAtVmax");
      }
      if (isometricSlope <= maxEccentricVelocityForceMultiplier-1) {
         throw new IllegalArgumentException (
            "isometricSlope must exceed (maxEccentricVelocityForceMultiplier-1)");
      }
      if (eccentricSlopeAtVmax < 0) {
         throw new IllegalArgumentException (
            "eccentricSlopeAtVmax must be >= 0");
      }
      if (eccentricSlopeAtVmax >= maxEccentricVelocityForceMultiplier-1) {
         throw new IllegalArgumentException (
            "eccentricSlopeAtVmax must be less than " +
            "(maxEccentricVelocityForceMultiplier-1)");
      }
      if (maxEccentricVelocityForceMultiplier <= 1) {
         throw new IllegalArgumentException (
            "maxEccentricVelocityForceMultiplier must be > 1");
      }
      if (concentricCurviness < 0 || concentricCurviness > 1) {
         throw new IllegalArgumentException (
            "concentricCurviness must be in the range [0,1]");
      }
      if (eccentricCurviness < 0 || eccentricCurviness > 1) {
         throw new IllegalArgumentException (
            "eccentricCurviness must be in the range [0,1]");
      }
      // need to make end points have small non-zero slopes to ensure curve is
      // invertible.

      CubicHermiteSpline1d curve = new CubicHermiteSpline1d();

      // start point at C
      double xC = -1.0;
      double yC = 0.0;
      double dydxC = concentricSlopeAtVmax;
      curve.addKnot (xC, yC, dydxC);

      // point near C
      double xNearC = -0.9;
      double dydxNearC = concentricSlopeNearVmax;
      double yNearC = yC + 0.5*dydxNearC*(xNearC-xC) + 0.5*dydxC*(xNearC-xC);
      curve.addKnot (xNearC, yNearC, dydxNearC);

      // ISO mid point
      curve.addKnot (0.0, 1.0, isometricSlope);

      // end point E
      double xE = 1.0;
      double yE = maxEccentricVelocityForceMultiplier;
      double dydxE = eccentricSlopeAtVmax;
      curve.addKnot (xE, yE, dydxE);

      // point near E
      double xNearE = 0.9;
      double dydxNearE = eccentricSlopeNearVmax;
      double yNearE =
         yE + 0.5*dydxNearE*(xNearE-xE) + 0.5*dydxE*(xNearE-xE);
      curve.addKnot (xNearE, yNearE, dydxNearE);

      // add extra knot to remove inflection between near C and ISO
      addAntiInflexionKnot (curve, 1);
      // add extra knot to remove inflection between near ISO and near E
      addAntiInflexionKnot (curve, 3);

      return curve;
   }

   public static CubicHermiteSpline1d createTendonForceLengthCurve (
      double strainAtOneNormForce,
      double stiffnessAtOneNormForce,
      double normForceAtToeEnd,
      double curviness) {

      if (strainAtOneNormForce <= 0) {
         throw new IllegalArgumentException (
            "strainAtOneNormForce must be > 0");
      }
      if (stiffnessAtOneNormForce <= 1/strainAtOneNormForce) {
         throw new IllegalArgumentException (
            "stiffnessAtOneNormForce must be > 1/strainAtOneNormForce");
      }
      if (normForceAtToeEnd <= 0 || normForceAtToeEnd >= 1) {
         throw new IllegalArgumentException (
            "normForceAtToeEnd must be in the range (0,1)");
      }
      if (curviness < 0 || curviness > 1) {
         throw new IllegalArgumentException (
            "curviness must be in the range [0,1]");
      }

      double eIso = strainAtOneNormForce;
      double kIso = stiffnessAtOneNormForce;
      double fToe = normForceAtToeEnd;

      CubicHermiteSpline1d curve = new CubicHermiteSpline1d();

      // start point
      curve.addKnot (1.0, 0.0, 0.0);

      // ISO point
      double xIso = 1.0 + strainAtOneNormForce;
      curve.addKnot (xIso, 1.0, stiffnessAtOneNormForce);
      
      // point between start and ISO where the curved section becomes linear
      curve.addKnot ((fToe-1)/kIso + xIso, fToe, stiffnessAtOneNormForce);
      
      return curve;
   }
      
   public static CubicHermiteSpline1d createPassiveForceLengthCurve (
      double strainAtZeroForce,
      double strainAtOneNormForce,
      double stiffnessAtLowForce,
      double stiffnessAtOneNormForce, 
      double curviness) {

      if (strainAtZeroForce >= strainAtOneNormForce) {
         throw new IllegalArgumentException (
            "strainAtZeroForce must be < strainAtOneNormForce");
      }
      if (stiffnessAtOneNormForce <= 1/(strainAtOneNormForce-strainAtZeroForce)) {
         throw new IllegalArgumentException (
            "stiffnessAtOneNormForce must be > "+
            "1/(strainAtOneNormForce-strainAtZeroForce)");
      }
      if (stiffnessAtLowForce <= 0) {
         throw new IllegalArgumentException (
            "stiffnessAtLowForce must be > 0");
      }
      if (stiffnessAtLowForce >= stiffnessAtOneNormForce) {
         throw new IllegalArgumentException (
            "stiffnessAtLowForce must be < stiffnessAtOneNormForce");
      }
      if (curviness < 0 || curviness > 1) {
         throw new IllegalArgumentException (
            "curviness must be in the range [0,1]");
      }
      
      double eZero = strainAtZeroForce;
      double eIso = strainAtOneNormForce;
      double kLow = stiffnessAtLowForce;
      double kIso = stiffnessAtOneNormForce;

      CubicHermiteSpline1d curve = new CubicHermiteSpline1d();

      // start point
      double xStart = 1.0 + eZero;
      curve.addKnot (xStart, 0.0, 0.0);

      // ISO point
      double xIso = 1.0 + eIso;
      curve.addKnot (xIso, 1.0, stiffnessAtOneNormForce);

      // intermediate knot between start and ISO
      double deltaX = Math.min(0.1*(1.0/kIso), 0.1*(xIso-xStart));
      double xLow = xStart + deltaX;
      double xfoot = xStart + 0.5*(xLow-xStart);
      curve.addKnot (xStart + deltaX, kLow*(xLow-xfoot), kLow);
      
      return curve;
   }

}

