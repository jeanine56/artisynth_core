/**
 * Copyright (c) 2015, by the Authors: Antonio Sanchez (UBC)
 *
 * This software is freely available under a 2-clause BSD license. Please see
 * the LICENSE file in the ArtiSynth distribution directory for details.
 */

package maspack.dicom;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Listing of known DICOM tags, and utilities for determining their default storage type
 * @author Antonio
 *
 */
public class DicomTag {
   
   public static final int NUMBER_OF_FRAMES = 0x00280008;
   public static final int SAMPLES_PER_PIXEL = 0x00280002;
   public static final int PHOTOMETRIC_ITERPRETATION = 0x00280004;
   public static final int PLANAR_CONFIGURATION = 0x00280006;
   public static final int ROWS = 0x00280010;
   public static final int COLUMNS = 0x00280011;
   public static final int BITS_ALLOCATED = 0x00280100;
   public static final int BITS_STORED = 0x00280101;
   public static final int HIGH_BIT = 0x00280102;
   public static final int PIXEL_REPRESENTATION = 0x00280103;
   public static final int SMALLEST_IMAGE_PIXEL_VALUE = 0x00280106;
   public static final int LARGEST_IMAGE_PIXEL_VALUE = 0x00280107;
   public static final int PIXEL_DATA = 0x7FE00010;
   
   public static final int WINDOW_CENTER = 0x00281050;
   public static final int WINDOW_WIDTH = 0x00281051;
   public static final int RESCALE_INTERCEPT = 0x00281052;
   public static final int RESCALE_SLOPE = 0x00281053;
   public static final int RESCALE_TYPE = 0x00281054;
   public static final int WINDOW_CENTER_AND_WIDTH_EXPLANATION = 0x00281055;
   
   public static final int STUDY_ID = 0x00200010;
   public static final int SERIES_NUMBER = 0x00200011;
   public static final int SERIES_TIME = 0x00080031;
   public static final int AQUISITION_NUMBER = 0x00200012;
   public static final int AQUISITION_TIME = 0x00080032;
   public static final int IMAGE_NUMBER = 0x00200013;
   public static final int IMAGE_TIME = 0x00080033;
   
   public static final int TEMPORAL_POSITON_IDENTIFIER = 0x00200100;
   public static final int TEMPORAL_RESOLUTION = 0x00200110;
   public static final int NUMBER_OF_TEMPORAL_POSITIONS = 0x00200105;
   
   public static final int IMAGE_POSITION_PATIENT = 0x00200032;
   public static final int IMAGE_ORIENTATION_PATIENT = 0x00200037;
   public static final int SLICE_LOCATION = 0x00201041;
   public static final int PIXEL_SPACING = 0x00280030;
   public static final int SLICE_THICKNESS = 0x00180050;
   public static final int SPACING_BETWEEN_SLICES = 0x00180088;
   
   public static final int TRANSFER_SYNTAX_UID = 0x00020010;
   
   public static final int ITEM = 0xFFFEE000;
   public static final int ITEM_DELIMINATION = 0xFFFEE00D;
   public static final int SEQUENCE_DELIMINATION = 0xFFFEE0DD;

   private static class TagInfo {
      int tag;
      DicomElement.VR implicitVR;
      String name;
      public TagInfo(int tag, DicomElement.VR implicitVR, String name) {
         this.tag = tag;
         this.implicitVR = implicitVR;
         this.name = name;
      }
      
      @Override
      public String toString() {
         return Integer.toHexString(tag) + ", " + name + ", " + implicitVR;
         
      }
   }

   private static HashMap<Integer,TagInfo> tagInfoMap = 
      new HashMap<Integer, TagInfo>(650);

   static {
      tagInfoMap.put( 0x00000000, new TagInfo(0x00000000, DicomElement.VR.UL, "Group 0000 Length") );
      tagInfoMap.put( 0x00000001, new TagInfo(0x00000001, DicomElement.VR.UL, "Group 0000 Length to End (RET)") );
      tagInfoMap.put( 0x00000002, new TagInfo(0x00000002, DicomElement.VR.UI, "Affected SOP Class UID") );
      tagInfoMap.put( 0x00000003, new TagInfo(0x00000003, DicomElement.VR.UI, "Requested SOP Class UID") );
      tagInfoMap.put( 0x00000010, new TagInfo(0x00000010, DicomElement.VR.SH, "Recognition Code (RET)") );
      tagInfoMap.put( 0x00000100, new TagInfo(0x00000100, DicomElement.VR.US, "Command Field") );
      tagInfoMap.put( 0x00000110, new TagInfo(0x00000110, DicomElement.VR.US, "Message ID") );
      tagInfoMap.put( 0x00000120, new TagInfo(0x00000120, DicomElement.VR.US, "Message Id being Responded to") );
      tagInfoMap.put( 0x00000200, new TagInfo(0x00000200, DicomElement.VR.AE, "Initiator (RET)") );
      tagInfoMap.put( 0x00000300, new TagInfo(0x00000300, DicomElement.VR.AE, "Receiver (RET)") );
      tagInfoMap.put( 0x00000400, new TagInfo(0x00000400, DicomElement.VR.AE, "Find Location (RET)") );
      tagInfoMap.put( 0x00000600, new TagInfo(0x00000600, DicomElement.VR.AE, "Move Destination") );
      tagInfoMap.put( 0x00000700, new TagInfo(0x00000700, DicomElement.VR.US, "Priority") );
      tagInfoMap.put( 0x00000800, new TagInfo(0x00000800, DicomElement.VR.US, "Data Set Type") );
      tagInfoMap.put( 0x00000850, new TagInfo(0x00000850, DicomElement.VR.US, "Number of Matches (RET)") );
      tagInfoMap.put( 0x00000860, new TagInfo(0x00000860, DicomElement.VR.US, "Response Sequence Number (RET)") );
      tagInfoMap.put( 0x00000900, new TagInfo(0x00000900, DicomElement.VR.US, "Status") );
      tagInfoMap.put( 0x00000901, new TagInfo(0x00000901, DicomElement.VR.AT, "Offending Element") );
      tagInfoMap.put( 0x00000902, new TagInfo(0x00000902, DicomElement.VR.LO, "Error Comment") );
      tagInfoMap.put( 0x00000903, new TagInfo(0x00000903, DicomElement.VR.US, "Error ID") );
      tagInfoMap.put( 0x00001000, new TagInfo(0x00001000, DicomElement.VR.UI, "Affected SOP Instance UID") );
      tagInfoMap.put( 0x00001001, new TagInfo(0x00001001, DicomElement.VR.UI, "Requested SOP Instance UID") );
      tagInfoMap.put( 0x00001002, new TagInfo(0x00001002, DicomElement.VR.US, "Event Type ID") );
      tagInfoMap.put( 0x00001005, new TagInfo(0x00001005, DicomElement.VR.AT, "Attribute Identifier List") );
      tagInfoMap.put( 0x00001008, new TagInfo(0x00001008, DicomElement.VR.US, "Action Type ID") );
      tagInfoMap.put( 0x00001012, new TagInfo(0x00001012, DicomElement.VR.UI, "Requested SOP Instance UID List") );
      tagInfoMap.put( 0x00001020, new TagInfo(0x00001020, DicomElement.VR.US, "Number of Remaining Sub-operations") );
      tagInfoMap.put( 0x00001021, new TagInfo(0x00001021, DicomElement.VR.US, "Number of Completed Sub-operations") );
      tagInfoMap.put( 0x00001022, new TagInfo(0x00001022, DicomElement.VR.US, "Number of Failed Sub-operations") );
      tagInfoMap.put( 0x00001023, new TagInfo(0x00001023, DicomElement.VR.US, "Number of Warning Sub-operations") );
      tagInfoMap.put( 0x00001030, new TagInfo(0x00001030, DicomElement.VR.AE, "Move Originator Application Entity Title") );
      tagInfoMap.put( 0x00001031, new TagInfo(0x00001031, DicomElement.VR.US, "Move Originator Message ID") );
      tagInfoMap.put( 0x00005010, new TagInfo(0x00005010, DicomElement.VR.LO, "Message Set ID (RET)") );
      tagInfoMap.put( 0x00005020, new TagInfo(0x00005020, DicomElement.VR.LO, "End Message Set ID (RET)") );
      tagInfoMap.put( 0x00020000, new TagInfo(0x00020000, DicomElement.VR.UL, "Group 0002 Length") );
      tagInfoMap.put( 0x00020001, new TagInfo(0x00020001, DicomElement.VR.OB, "File Meta Information Version") );
      tagInfoMap.put( 0x00020002, new TagInfo(0x00020002, DicomElement.VR.UI, "Media Stored SOP Class UID") );
      tagInfoMap.put( 0x00020003, new TagInfo(0x00020003, DicomElement.VR.UI, "Media Stored SOP Instance UID") );
      tagInfoMap.put( 0x00020010, new TagInfo(0x00020010, DicomElement.VR.UI, "Transfer Syntax UID") );
      tagInfoMap.put( 0x00020012, new TagInfo(0x00020012, DicomElement.VR.UI, "Implementation Class UID") );
      tagInfoMap.put( 0x00020013, new TagInfo(0x00020013, DicomElement.VR.SH, "Implementation Version Name") );
      tagInfoMap.put( 0x00020016, new TagInfo(0x00020016, DicomElement.VR.AE, "Source Application Entity Title") );
      tagInfoMap.put( 0x00020100, new TagInfo(0x00020100, DicomElement.VR.UI, "Private Information Creator UID") );
      tagInfoMap.put( 0x00020102, new TagInfo(0x00020102, DicomElement.VR.OB, "Private Information") );
      tagInfoMap.put( 0x00040000, new TagInfo(0x00040000, DicomElement.VR.UL, "Group 0004 Length") );
      tagInfoMap.put( 0x00041130, new TagInfo(0x00041130, DicomElement.VR.CS, "File-set ID") );
      tagInfoMap.put( 0x00041141, new TagInfo(0x00041141, DicomElement.VR.CS, "File-set Descriptor File File ID") );
      tagInfoMap.put( 0x00041142, new TagInfo(0x00041142, DicomElement.VR.CS, "File-set Descriptor File Format") );
      tagInfoMap.put( 0x00041200, new TagInfo(0x00041200, DicomElement.VR.UL, "Root Directory Entity's First Directory Record Offset") );
      tagInfoMap.put( 0x00041202, new TagInfo(0x00041202, DicomElement.VR.UL, "Root Directory Entity's Last Directory Record Offset") );
      tagInfoMap.put( 0x00041212, new TagInfo(0x00041212, DicomElement.VR.US, "File-set Consistence Flag") );
      tagInfoMap.put( 0x00041220, new TagInfo(0x00041220, DicomElement.VR.SQ, "Directory Record Sequence") );
      tagInfoMap.put( 0x00041400, new TagInfo(0x00041400, DicomElement.VR.UL, "Next Directory Record Offset") );
      tagInfoMap.put( 0x00041410, new TagInfo(0x00041410, DicomElement.VR.US, "Record In-use Flag") );
      tagInfoMap.put( 0x00041420, new TagInfo(0x00041420, DicomElement.VR.UL, "Referenced Lower-level Directory Entity Offset") );
      tagInfoMap.put( 0x00041430, new TagInfo(0x00041430, DicomElement.VR.CS, "Directory Record Type") );
      tagInfoMap.put( 0x00041432, new TagInfo(0x00041432, DicomElement.VR.UI, "Private Record UID") );
      tagInfoMap.put( 0x00041500, new TagInfo(0x00041500, DicomElement.VR.CS, "Referenced File ID") );
      tagInfoMap.put( 0x00041510, new TagInfo(0x00041510, DicomElement.VR.UI, "Referenced SOP Class UID in File") );
      tagInfoMap.put( 0x00041511, new TagInfo(0x00041511, DicomElement.VR.UI, "Referenced SOP Instance UID in File") );
      tagInfoMap.put( 0x00041600, new TagInfo(0x00041600, DicomElement.VR.UL, "Number of References") );
      tagInfoMap.put( 0x00080000, new TagInfo(0x00080000, DicomElement.VR.UL, "Group 0008 Length") );
      tagInfoMap.put( 0x00080001, new TagInfo(0x00080001, DicomElement.VR.UL, "Group 0008 Length to End (RET)") );
      tagInfoMap.put( 0x00080005, new TagInfo(0x00080005, DicomElement.VR.CS, "Specific Character Set") );
      tagInfoMap.put( 0x00080008, new TagInfo(0x00080008, DicomElement.VR.CS, "Image Type") );
      tagInfoMap.put( 0x00080010, new TagInfo(0x00080010, DicomElement.VR.SH, "Recognition Code (RET)") );
      tagInfoMap.put( 0x00080012, new TagInfo(0x00080012, DicomElement.VR.DA, "Instance Creation Date") );
      tagInfoMap.put( 0x00080013, new TagInfo(0x00080013, DicomElement.VR.TM, "Instance Creation Time") );
      tagInfoMap.put( 0x00080014, new TagInfo(0x00080014, DicomElement.VR.UI, "Instance Creator UID") );
      tagInfoMap.put( 0x00080016, new TagInfo(0x00080016, DicomElement.VR.UI, "SOP Class UID") );
      tagInfoMap.put( 0x00080018, new TagInfo(0x00080018, DicomElement.VR.UI, "SOP Instance UID") );
      tagInfoMap.put( 0x00080020, new TagInfo(0x00080020, DicomElement.VR.DA, "Study Date") );
      tagInfoMap.put( 0x00080021, new TagInfo(0x00080021, DicomElement.VR.DA, "Series Date") );
      tagInfoMap.put( 0x00080022, new TagInfo(0x00080022, DicomElement.VR.DA, "Acquisition Date") );
      tagInfoMap.put( 0x00080023, new TagInfo(0x00080023, DicomElement.VR.DA, "Image Date") );
      tagInfoMap.put( 0x00080024, new TagInfo(0x00080024, DicomElement.VR.DA, "Overlay Date") );
      tagInfoMap.put( 0x00080025, new TagInfo(0x00080025, DicomElement.VR.DA, "Curve Date") );
      tagInfoMap.put( 0x00080030, new TagInfo(0x00080030, DicomElement.VR.TM, "Study Time") );
      tagInfoMap.put( 0x00080031, new TagInfo(0x00080031, DicomElement.VR.TM, "Series Time") );
      tagInfoMap.put( 0x00080032, new TagInfo(0x00080032, DicomElement.VR.TM, "Acquisition Time") );
      tagInfoMap.put( 0x00080033, new TagInfo(0x00080033, DicomElement.VR.TM, "Image Time") );
      tagInfoMap.put( 0x00080034, new TagInfo(0x00080034, DicomElement.VR.TM, "Overlay Time") );
      tagInfoMap.put( 0x00080035, new TagInfo(0x00080035, DicomElement.VR.TM, "Curve Time") );
      tagInfoMap.put( 0x00080040, new TagInfo(0x00080040, DicomElement.VR.US, "Data Set Type (RET)") );
      tagInfoMap.put( 0x00080041, new TagInfo(0x00080041, DicomElement.VR.SH, "Data Set Subtype (RET)") );
      tagInfoMap.put( 0x00080042, new TagInfo(0x00080042, DicomElement.VR.CS, "Nuclear Medicine Series Type") );
      tagInfoMap.put( 0x00080050, new TagInfo(0x00080050, DicomElement.VR.SH, "Accession Number") );
      tagInfoMap.put( 0x00080052, new TagInfo(0x00080052, DicomElement.VR.CS, "Query/Retrieve Level") );
      tagInfoMap.put( 0x00080054, new TagInfo(0x00080054, DicomElement.VR.AE, "Retrieve AE Title") );
      tagInfoMap.put( 0x00080058, new TagInfo(0x00080058, DicomElement.VR.AE, "Failed SOP Instance UID List") );
      tagInfoMap.put( 0x00080060, new TagInfo(0x00080060, DicomElement.VR.CS, "Modality") );
      tagInfoMap.put( 0x00080064, new TagInfo(0x00080064, DicomElement.VR.CS, "Conversion Type") );
      tagInfoMap.put( 0x00080070, new TagInfo(0x00080070, DicomElement.VR.LO, "Manufacturer") );
      tagInfoMap.put( 0x00080080, new TagInfo(0x00080080, DicomElement.VR.LO, "Institution Name") );
      tagInfoMap.put( 0x00080081, new TagInfo(0x00080081, DicomElement.VR.ST, "Institution Address") );
      tagInfoMap.put( 0x00080082, new TagInfo(0x00080082, DicomElement.VR.SQ, "Institution Code Sequence") );
      tagInfoMap.put( 0x00080090, new TagInfo(0x00080090, DicomElement.VR.PN, "Referring Physician's Name") );
      tagInfoMap.put( 0x00080092, new TagInfo(0x00080092, DicomElement.VR.ST, "Referring Physician's Address") );
      tagInfoMap.put( 0x00080094, new TagInfo(0x00080094, DicomElement.VR.SH, "Referring Physician's Telephone Numbers") );
      tagInfoMap.put( 0x00080100, new TagInfo(0x00080100, DicomElement.VR.SH, "Code Value") );
      tagInfoMap.put( 0x00080102, new TagInfo(0x00080102, DicomElement.VR.SH, "Coding Scheme Designator") );
      tagInfoMap.put( 0x00080104, new TagInfo(0x00080104, DicomElement.VR.LO, "Code Meaning") );
      tagInfoMap.put( 0x00081000, new TagInfo(0x00081000, DicomElement.VR.SH, "Network ID (RET)") );
      tagInfoMap.put( 0x00081010, new TagInfo(0x00081010, DicomElement.VR.SH, "Station Name") );
      tagInfoMap.put( 0x00081030, new TagInfo(0x00081030, DicomElement.VR.LO, "Study Description") );
      tagInfoMap.put( 0x00081032, new TagInfo(0x00081032, DicomElement.VR.SQ, "Procedure Code Sequence") );
      tagInfoMap.put( 0x0008103E, new TagInfo(0x0008103E, DicomElement.VR.LO, "Series Description") );
      tagInfoMap.put( 0x00081040, new TagInfo(0x00081040, DicomElement.VR.LO, "Institutional Department Name") );
      tagInfoMap.put( 0x00081050, new TagInfo(0x00081050, DicomElement.VR.PN, "Attending Physician's Name") );
      tagInfoMap.put( 0x00081060, new TagInfo(0x00081060, DicomElement.VR.PN, "Name of Physician(s) Reading Study") );
      tagInfoMap.put( 0x00081070, new TagInfo(0x00081070, DicomElement.VR.PN, "Operator's Name") );
      tagInfoMap.put( 0x00081080, new TagInfo(0x00081080, DicomElement.VR.LO, "Admitting Diagnoses Description") );
      tagInfoMap.put( 0x00081084, new TagInfo(0x00081084, DicomElement.VR.SQ, "Admitting Diagnosis Code Sequence") );
      tagInfoMap.put( 0x00081090, new TagInfo(0x00081090, DicomElement.VR.LO, "Manufacturer's Model Name") );
      tagInfoMap.put( 0x00081100, new TagInfo(0x00081100, DicomElement.VR.SQ, "Referenced Results Sequence") );
      tagInfoMap.put( 0x00081110, new TagInfo(0x00081110, DicomElement.VR.SQ, "Referenced Study Sequence") );
      tagInfoMap.put( 0x00081111, new TagInfo(0x00081111, DicomElement.VR.SQ, "Referenced Study Component Sequence") );
      tagInfoMap.put( 0x00081115, new TagInfo(0x00081115, DicomElement.VR.SQ, "Referenced Series Sequence") );
      tagInfoMap.put( 0x00081120, new TagInfo(0x00081120, DicomElement.VR.SQ, "Referenced Patient Sequence") );
      tagInfoMap.put( 0x00081125, new TagInfo(0x00081125, DicomElement.VR.SQ, "Referenced Visit Sequence") );
      tagInfoMap.put( 0x00081130, new TagInfo(0x00081130, DicomElement.VR.SQ, "Referenced Overlay Sequence") );
      tagInfoMap.put( 0x00081140, new TagInfo(0x00081140, DicomElement.VR.SQ, "Referenced Image Sequence") );
      tagInfoMap.put( 0x00081145, new TagInfo(0x00081145, DicomElement.VR.SQ, "Referenced Curve Sequence") );
      tagInfoMap.put( 0x00081150, new TagInfo(0x00081150, DicomElement.VR.UI, "Referenced SOP Class UID") );
      tagInfoMap.put( 0x00081155, new TagInfo(0x00081155, DicomElement.VR.UI, "Referenced SOP Instance UID") );
      tagInfoMap.put( 0x00082111, new TagInfo(0x00082111, DicomElement.VR.ST, "Derivation Description") );
      tagInfoMap.put( 0x00082112, new TagInfo(0x00082112, DicomElement.VR.SQ, "Source Image Sequence") );
      tagInfoMap.put( 0x00082120, new TagInfo(0x00082120, DicomElement.VR.SH, "Stage Name") );
      tagInfoMap.put( 0x00082122, new TagInfo(0x00082122, DicomElement.VR.IS, "Stage Number") );
      tagInfoMap.put( 0x00082124, new TagInfo(0x00082124, DicomElement.VR.IS, "Number of Stages") );
      tagInfoMap.put( 0x00082129, new TagInfo(0x00082129, DicomElement.VR.IS, "Number of Event Timers") );
      tagInfoMap.put( 0x00082128, new TagInfo(0x00082128, DicomElement.VR.IS, "View Number") );
      tagInfoMap.put( 0x0008212A, new TagInfo(0x0008212A, DicomElement.VR.IS, "Number of Views in Stage") );
      tagInfoMap.put( 0x00082130, new TagInfo(0x00082130, DicomElement.VR.DS, "Event Elapsed Time(s)") );
      tagInfoMap.put( 0x00082132, new TagInfo(0x00082132, DicomElement.VR.LO, "Event Timer Name(s)") );
      tagInfoMap.put( 0x00082142, new TagInfo(0x00082142, DicomElement.VR.IS, "Start Trim") );
      tagInfoMap.put( 0x00082143, new TagInfo(0x00082143, DicomElement.VR.IS, "Stop Trim") );
      tagInfoMap.put( 0x00082144, new TagInfo(0x00082144, DicomElement.VR.IS, "Recommended Display Frame Rate") );
      tagInfoMap.put( 0x00082200, new TagInfo(0x00082200, DicomElement.VR.CS, "Transducer Position") );
      tagInfoMap.put( 0x00082204, new TagInfo(0x00082204, DicomElement.VR.CS, "Transducer Orientation") );
      tagInfoMap.put( 0x00082208, new TagInfo(0x00082208, DicomElement.VR.CS, "Anatomic Structure") );
      tagInfoMap.put( 0x00084000, new TagInfo(0x00084000, DicomElement.VR.SH, "Group 0008 Comments (RET)") );
      tagInfoMap.put( 0x00100000, new TagInfo(0x00100000, DicomElement.VR.UL, "Group 0010 Length") );
      tagInfoMap.put( 0x00100010, new TagInfo(0x00100010, DicomElement.VR.PN, "Patient's Name") );
      tagInfoMap.put( 0x00100020, new TagInfo(0x00100020, DicomElement.VR.LO, "Patient ID") );
      tagInfoMap.put( 0x00100021, new TagInfo(0x00100021, DicomElement.VR.LO, "Issuer of Patient ID") );
      tagInfoMap.put( 0x00100030, new TagInfo(0x00100030, DicomElement.VR.DA, "Patient's Birth Date") );
      tagInfoMap.put( 0x00100032, new TagInfo(0x00100032, DicomElement.VR.TM, "Patient's Birth Time") );
      tagInfoMap.put( 0x00100040, new TagInfo(0x00100040, DicomElement.VR.CS, "Patient's Sex") );
      tagInfoMap.put( 0x00100042, new TagInfo(0x00100042, DicomElement.VR.SH, "Patient's Social Security Number") );
      tagInfoMap.put( 0x00100050, new TagInfo(0x00100050, DicomElement.VR.SQ, "Patient's Insurance Plan Code Sequence") );
      tagInfoMap.put( 0x00101000, new TagInfo(0x00101000, DicomElement.VR.LO, "Other Patient IDs") );
      tagInfoMap.put( 0x00101001, new TagInfo(0x00101001, DicomElement.VR.PN, "Other Patient Names") );
      tagInfoMap.put( 0x00101005, new TagInfo(0x00101005, DicomElement.VR.PN, "Patient's Maiden Name") );
      tagInfoMap.put( 0x00101010, new TagInfo(0x00101010, DicomElement.VR.AS, "Patient's Age") );
      tagInfoMap.put( 0x00101020, new TagInfo(0x00101020, DicomElement.VR.DS, "Patient's Size") );
      tagInfoMap.put( 0x00101030, new TagInfo(0x00101030, DicomElement.VR.DS, "Patient's Weight") );
      tagInfoMap.put( 0x00101040, new TagInfo(0x00101040, DicomElement.VR.LO, "Patient's Address") );
      tagInfoMap.put( 0x00101050, new TagInfo(0x00101050, DicomElement.VR.SH, "Insurance Plan Identification (RET)") );
      tagInfoMap.put( 0x00101060, new TagInfo(0x00101060, DicomElement.VR.PN, "Patient's Mother's Maiden Name") );
      tagInfoMap.put( 0x00101080, new TagInfo(0x00101080, DicomElement.VR.LO, "Military Rank") );
      tagInfoMap.put( 0x00101081, new TagInfo(0x00101081, DicomElement.VR.LO, "Branch of Service") );
      tagInfoMap.put( 0x00101090, new TagInfo(0x00101090, DicomElement.VR.LO, "Medical Record Locator") );
      tagInfoMap.put( 0x00102000, new TagInfo(0x00102000, DicomElement.VR.LO, "Medical Alerts") );
      tagInfoMap.put( 0x00102110, new TagInfo(0x00102110, DicomElement.VR.LO, "Contrast Allergies") );
      tagInfoMap.put( 0x00102150, new TagInfo(0x00102150, DicomElement.VR.LO, "Country of Residence") );
      tagInfoMap.put( 0x00102152, new TagInfo(0x00102152, DicomElement.VR.LO, "Region of Residence") );
      tagInfoMap.put( 0x00102154, new TagInfo(0x00102154, DicomElement.VR.SH, "Patient's Telephone Numbers") );
      tagInfoMap.put( 0x00102160, new TagInfo(0x00102160, DicomElement.VR.SH, "Ethnic Group") );
      tagInfoMap.put( 0x00102180, new TagInfo(0x00102180, DicomElement.VR.SH, "Occupation") );
      tagInfoMap.put( 0x001021A0, new TagInfo(0x001021A0, DicomElement.VR.CS, "Smoking Status") );
      tagInfoMap.put( 0x001021B0, new TagInfo(0x001021B0, DicomElement.VR.LT, "Additional Patient History") );
      tagInfoMap.put( 0x001021C0, new TagInfo(0x001021C0, DicomElement.VR.US, "Pregnancy Status") );
      tagInfoMap.put( 0x001021D0, new TagInfo(0x001021D0, DicomElement.VR.DA, "Last Menstrual Date") );
      tagInfoMap.put( 0x001021F0, new TagInfo(0x001021F0, DicomElement.VR.LO, "Patient's Religious Preference") );
      tagInfoMap.put( 0x00104000, new TagInfo(0x00104000, DicomElement.VR.LT, "Patient Comments") );
      tagInfoMap.put( 0x00180000, new TagInfo(0x00180000, DicomElement.VR.UL, "Group 0018 Length") );
      tagInfoMap.put( 0x00180010, new TagInfo(0x00180010, DicomElement.VR.LO, "Contrast/Bolus Agent") );
      tagInfoMap.put( 0x00180015, new TagInfo(0x00180015, DicomElement.VR.CS, "Body Part Examined") );
      tagInfoMap.put( 0x00180020, new TagInfo(0x00180020, DicomElement.VR.CS, "Scanning Sequence") );
      tagInfoMap.put( 0x00180021, new TagInfo(0x00180021, DicomElement.VR.CS, "Sequence Variant") );
      tagInfoMap.put( 0x00180022, new TagInfo(0x00180022, DicomElement.VR.CS, "Scan Options") );
      tagInfoMap.put( 0x00180023, new TagInfo(0x00180023, DicomElement.VR.CS, "MR Acquisition Type") );
      tagInfoMap.put( 0x00180024, new TagInfo(0x00180024, DicomElement.VR.SH, "Sequence Name") );
      tagInfoMap.put( 0x00180025, new TagInfo(0x00180025, DicomElement.VR.CS, "Angio Flag") );
      tagInfoMap.put( 0x00180030, new TagInfo(0x00180030, DicomElement.VR.LO, "Radionuclide") );
      tagInfoMap.put( 0x00180031, new TagInfo(0x00180031, DicomElement.VR.LO, "Radiopharmaceutical") );
      tagInfoMap.put( 0x00180032, new TagInfo(0x00180032, DicomElement.VR.DS, "Energy Window Centerline") );
      tagInfoMap.put( 0x00180033, new TagInfo(0x00180033, DicomElement.VR.DS, "Energy Window Total Width") );
      tagInfoMap.put( 0x00180034, new TagInfo(0x00180034, DicomElement.VR.LO, "Intervention Drug Name") );
      tagInfoMap.put( 0x00180035, new TagInfo(0x00180035, DicomElement.VR.TM, "Intervention Drug Start Time") );
      tagInfoMap.put( 0x00180040, new TagInfo(0x00180040, DicomElement.VR.IS, "Cine Rate") );
      tagInfoMap.put( 0x00180050, new TagInfo(0x00180050, DicomElement.VR.DS, "Slice Thickness") );
      tagInfoMap.put( 0x00180060, new TagInfo(0x00180060, DicomElement.VR.DS, "KVP") );
      tagInfoMap.put( 0x00180070, new TagInfo(0x00180070, DicomElement.VR.IS, "Counts Accumulated") );
      tagInfoMap.put( 0x00180071, new TagInfo(0x00180071, DicomElement.VR.CS, "Acquisition Termination Condition") );
      tagInfoMap.put( 0x00180072, new TagInfo(0x00180072, DicomElement.VR.DS, "Effective Series Duration") );
      tagInfoMap.put( 0x00180080, new TagInfo(0x00180080, DicomElement.VR.DS, "Repetition Time") );
      tagInfoMap.put( 0x00180081, new TagInfo(0x00180081, DicomElement.VR.DS, "Echo Time") );
      tagInfoMap.put( 0x00180082, new TagInfo(0x00180082, DicomElement.VR.DS, "Inversion Time") );
      tagInfoMap.put( 0x00180083, new TagInfo(0x00180083, DicomElement.VR.DS, "Number of Averages") );
      tagInfoMap.put( 0x00180084, new TagInfo(0x00180084, DicomElement.VR.DS, "Imaging Frequency") );
      tagInfoMap.put( 0x00180085, new TagInfo(0x00180085, DicomElement.VR.SH, "Imaged Nucleus") );
      tagInfoMap.put( 0x00180086, new TagInfo(0x00180086, DicomElement.VR.IS, "Echo Numbers(s)") );
      tagInfoMap.put( 0x00180087, new TagInfo(0x00180087, DicomElement.VR.DS, "Magnetic Field Strength") );
      tagInfoMap.put( 0x00180088, new TagInfo(0x00180088, DicomElement.VR.DS, "Spacing Between Slices") );
      tagInfoMap.put( 0x00180089, new TagInfo(0x00180089, DicomElement.VR.IS, "Number of Phase Encoding Steps") );
      tagInfoMap.put( 0x00180090, new TagInfo(0x00180090, DicomElement.VR.DS, "Data Collection Diameter") );
      tagInfoMap.put( 0x00180091, new TagInfo(0x00180091, DicomElement.VR.IS, "Echo Train Length") );
      tagInfoMap.put( 0x00180093, new TagInfo(0x00180093, DicomElement.VR.DS, "Percent Sampling") );
      tagInfoMap.put( 0x00180094, new TagInfo(0x00180094, DicomElement.VR.DS, "Percent Phase Field of View") );
      tagInfoMap.put( 0x00180095, new TagInfo(0x00180095, DicomElement.VR.DS, "Pixel Bandwidth") );
      tagInfoMap.put( 0x00181000, new TagInfo(0x00181000, DicomElement.VR.LO, "Device Serial Number") );
      tagInfoMap.put( 0x00181004, new TagInfo(0x00181004, DicomElement.VR.LO, "Plate ID") );
      tagInfoMap.put( 0x00181010, new TagInfo(0x00181010, DicomElement.VR.LO, "Secondary Capture Device ID") );
      tagInfoMap.put( 0x00181012, new TagInfo(0x00181012, DicomElement.VR.DA, "Date of Secondary Capture") );
      tagInfoMap.put( 0x00181014, new TagInfo(0x00181014, DicomElement.VR.TM, "Time of Secondary Capture") );
      tagInfoMap.put( 0x00181016, new TagInfo(0x00181016, DicomElement.VR.LO, "Secondary Capture Device Manufacturer") );
      tagInfoMap.put( 0x00181018, new TagInfo(0x00181018, DicomElement.VR.LO, "Secondary Capture Device Manufacturer's Model Name") );
      tagInfoMap.put( 0x00181019, new TagInfo(0x00181019, DicomElement.VR.LO, "Secondary Capture Device Software Version(s)") );
      tagInfoMap.put( 0x00181020, new TagInfo(0x00181020, DicomElement.VR.LO, "Software Versions(s)") );
      tagInfoMap.put( 0x00181022, new TagInfo(0x00181022, DicomElement.VR.SH, "Video Image Format Acquired") );
      tagInfoMap.put( 0x00181023, new TagInfo(0x00181023, DicomElement.VR.LO, "Digital Image Format Acquired") );
      tagInfoMap.put( 0x00181030, new TagInfo(0x00181030, DicomElement.VR.LO, "Protocol Name") );
      tagInfoMap.put( 0x00181040, new TagInfo(0x00181040, DicomElement.VR.LO, "Contrast/Bolus Route") );
      tagInfoMap.put( 0x00181041, new TagInfo(0x00181041, DicomElement.VR.DS, "Contrast/Bolus Volume") );
      tagInfoMap.put( 0x00181042, new TagInfo(0x00181042, DicomElement.VR.TM, "Contrast/Bolus Start Time") );
      tagInfoMap.put( 0x00181043, new TagInfo(0x00181043, DicomElement.VR.TM, "Contrast/Bolus Stop Time") );
      tagInfoMap.put( 0x00181044, new TagInfo(0x00181044, DicomElement.VR.DS, "Contrast/Bolus Total Dose") );
      tagInfoMap.put( 0x00181045, new TagInfo(0x00181045, DicomElement.VR.IS, "Syringe Counts") );
      tagInfoMap.put( 0x00181046, new TagInfo(0x00181050, DicomElement.VR.DS, "Contrast Flow Rate") );
      tagInfoMap.put( 0x00181047, new TagInfo(0x00181050, DicomElement.VR.DS, "Contrast Flow Duration") );
      tagInfoMap.put( 0x00181048, new TagInfo(0x00181050, DicomElement.VR.CS, "Contrast/Bolus Ingredient") );
      tagInfoMap.put( 0x00181049, new TagInfo(0x00181050, DicomElement.VR.DS, "Contrast/Bolus Ingredient Concentration") );
      tagInfoMap.put( 0x00181050, new TagInfo(0x00181050, DicomElement.VR.DS, "Spatial Resolution") );
      tagInfoMap.put( 0x00181060, new TagInfo(0x00181060, DicomElement.VR.DS, "Trigger Time") );
      tagInfoMap.put( 0x00181061, new TagInfo(0x00181061, DicomElement.VR.LO, "Trigger Source or Type") );
      tagInfoMap.put( 0x00181062, new TagInfo(0x00181062, DicomElement.VR.IS, "Nominal Interval") );
      tagInfoMap.put( 0x00181063, new TagInfo(0x00181063, DicomElement.VR.DS, "Frame Time") );
      tagInfoMap.put( 0x00181064, new TagInfo(0x00181064, DicomElement.VR.LO, "Framing Type") );
      tagInfoMap.put( 0x00181065, new TagInfo(0x00181065, DicomElement.VR.DS, "Frame Time Vector") );
      tagInfoMap.put( 0x00181066, new TagInfo(0x00181066, DicomElement.VR.DS, "Frame Delay") );
      tagInfoMap.put( 0x00181070, new TagInfo(0x00181070, DicomElement.VR.LO, "Radionuclide Route") );
      tagInfoMap.put( 0x00181071, new TagInfo(0x00181071, DicomElement.VR.DS, "Radionuclide Volume") );
      tagInfoMap.put( 0x00181072, new TagInfo(0x00181072, DicomElement.VR.TM, "Radionuclide Start Time") );
      tagInfoMap.put( 0x00181073, new TagInfo(0x00181073, DicomElement.VR.TM, "Radionuclide Stop Time") );
      tagInfoMap.put( 0x00181074, new TagInfo(0x00181074, DicomElement.VR.DS, "Radionuclide Total Dose") );
      tagInfoMap.put( 0x00181080, new TagInfo(0x00181080, DicomElement.VR.CS, "Beat Rejection Flag") );
      tagInfoMap.put( 0x00181081, new TagInfo(0x00181081, DicomElement.VR.IS, "Low R-R Value") );
      tagInfoMap.put( 0x00181082, new TagInfo(0x00181082, DicomElement.VR.IS, "High R-R Value") );
      tagInfoMap.put( 0x00181083, new TagInfo(0x00181083, DicomElement.VR.IS, "Intervals Acquired") );
      tagInfoMap.put( 0x00181084, new TagInfo(0x00181084, DicomElement.VR.IS, "Intervals Rejected") );
      tagInfoMap.put( 0x00181085, new TagInfo(0x00181085, DicomElement.VR.LO, "PVC Rejection") );
      tagInfoMap.put( 0x00181086, new TagInfo(0x00181086, DicomElement.VR.IS, "Skip Beats") );
      tagInfoMap.put( 0x00181088, new TagInfo(0x00181088, DicomElement.VR.IS, "Heart Rate") );
      tagInfoMap.put( 0x00181090, new TagInfo(0x00181090, DicomElement.VR.IS, "Cardiac Number of Images") );
      tagInfoMap.put( 0x00181094, new TagInfo(0x00181094, DicomElement.VR.IS, "Trigger Window") );
      tagInfoMap.put( 0x00181100, new TagInfo(0x00181100, DicomElement.VR.DS, "Reconstruction Diameter") );
      tagInfoMap.put( 0x00181110, new TagInfo(0x00181110, DicomElement.VR.DS, "Distance Source to Detector") );
      tagInfoMap.put( 0x00181111, new TagInfo(0x00181111, DicomElement.VR.DS, "Distance Source to Patient") );
      tagInfoMap.put( 0x00181120, new TagInfo(0x00181120, DicomElement.VR.DS, "Gantry/Detector Tilt") );
      tagInfoMap.put( 0x00181030, new TagInfo(0x00181030, DicomElement.VR.DS, "Table Height") );
      tagInfoMap.put( 0x00181131, new TagInfo(0x00181131, DicomElement.VR.DS, "Table Traverse") );
      tagInfoMap.put( 0x00181140, new TagInfo(0x00181140, DicomElement.VR.CS, "Rotation Direction") );
      tagInfoMap.put( 0x00181141, new TagInfo(0x00181141, DicomElement.VR.DS, "Angular Position") );
      tagInfoMap.put( 0x00181142, new TagInfo(0x00181142, DicomElement.VR.DS, "Radial Position") );
      tagInfoMap.put( 0x00181143, new TagInfo(0x00181143, DicomElement.VR.DS, "Scan Arc") );
      tagInfoMap.put( 0x00181144, new TagInfo(0x00181144, DicomElement.VR.DS, "Angular Step") );
      tagInfoMap.put( 0x00181145, new TagInfo(0x00181145, DicomElement.VR.DS, "Center of Rotation Offset") );
      tagInfoMap.put( 0x00181146, new TagInfo(0x00181146, DicomElement.VR.DS, "Rotation Offset") );
      tagInfoMap.put( 0x00181147, new TagInfo(0x00181147, DicomElement.VR.CS, "Field of View Shape") );
      tagInfoMap.put( 0x00181149, new TagInfo(0x00181149, DicomElement.VR.IS, "Field of View Dimensions(s)") );
      tagInfoMap.put( 0x00181150, new TagInfo(0x00181150, DicomElement.VR.IS, "Exposure Time") );
      tagInfoMap.put( 0x00181151, new TagInfo(0x00181151, DicomElement.VR.IS, "X-ray Tube Current") );
      tagInfoMap.put( 0x00181152, new TagInfo(0x00181152, DicomElement.VR.IS, "Exposure") );
      tagInfoMap.put( 0x00181160, new TagInfo(0x00181160, DicomElement.VR.SH, "Filter Type") );
      tagInfoMap.put( 0x00181170, new TagInfo(0x00181170, DicomElement.VR.IS, "Generator Power") );
      tagInfoMap.put( 0x00181180, new TagInfo(0x00181180, DicomElement.VR.SH, "Collimator/grid Name") );
      tagInfoMap.put( 0x00181181, new TagInfo(0x00181181, DicomElement.VR.CS, "Collimator Type") );
      tagInfoMap.put( 0x00181182, new TagInfo(0x00181182, DicomElement.VR.IS, "Focal Distance") );
      tagInfoMap.put( 0x00181183, new TagInfo(0x00181183, DicomElement.VR.DS, "X Focus Center") );
      tagInfoMap.put( 0x00181184, new TagInfo(0x00181184, DicomElement.VR.DS, "Y Focus Center") );
      tagInfoMap.put( 0x00181190, new TagInfo(0x00181190, DicomElement.VR.DS, "Focal Spot(s)") );
      tagInfoMap.put( 0x00181200, new TagInfo(0x00181200, DicomElement.VR.DA, "Date of Last Calibration") );
      tagInfoMap.put( 0x00181201, new TagInfo(0x00181201, DicomElement.VR.TM, "Time of Last Calibration") );
      tagInfoMap.put( 0x00181210, new TagInfo(0x00181210, DicomElement.VR.SH, "Convolution Kernel") );
      tagInfoMap.put( 0x00181240, new TagInfo(0x00181240, DicomElement.VR.DS, "Upper/Lower Pixel Values (RET)") );
      tagInfoMap.put( 0x00181242, new TagInfo(0x00181242, DicomElement.VR.IS, "Actual Frame Duration") );
      tagInfoMap.put( 0x00181243, new TagInfo(0x00181243, DicomElement.VR.IS, "Count Rate") );
      tagInfoMap.put( 0x00181250, new TagInfo(0x00181250, DicomElement.VR.SH, "Receiving Coil") );
      tagInfoMap.put( 0x00181251, new TagInfo(0x00181151, DicomElement.VR.SH, "Transmitting Coil") );
      tagInfoMap.put( 0x00181160, new TagInfo(0x00181160, DicomElement.VR.SH, "Screen Type") );
      tagInfoMap.put( 0x00181261, new TagInfo(0x00181261, DicomElement.VR.LO, "Phosphor Type") );
      tagInfoMap.put( 0x00181300, new TagInfo(0x00181300, DicomElement.VR.IS, "Scan Velocity") );
      tagInfoMap.put( 0x00181301, new TagInfo(0x00181301, DicomElement.VR.CS, "Whole Body Technique") );
      tagInfoMap.put( 0x00181302, new TagInfo(0x00181302, DicomElement.VR.IS, "Scan Length") );
      tagInfoMap.put( 0x00181310, new TagInfo(0x00181310, DicomElement.VR.US, "Acquisition Matrix") );
      tagInfoMap.put( 0x00181312, new TagInfo(0x00181312, DicomElement.VR.CS, "Phase Encoding Direction") );
      tagInfoMap.put( 0x00181314, new TagInfo(0x00181314, DicomElement.VR.DS, "Flip Angle") );
      tagInfoMap.put( 0x00181315, new TagInfo(0x00181315, DicomElement.VR.CS, "Variable Flip Angle Flag") );
      tagInfoMap.put( 0x00181316, new TagInfo(0x00181316, DicomElement.VR.DS, "SAR") );
      tagInfoMap.put( 0x00181318, new TagInfo(0x00181318, DicomElement.VR.DS, "dB/dt") );
      tagInfoMap.put( 0x00181400, new TagInfo(0x00181400, DicomElement.VR.LO, "Acquisition Device Processing Description") );
      tagInfoMap.put( 0x00181401, new TagInfo(0x00181401, DicomElement.VR.LO, "Acquisition Device Processing Code") );
      tagInfoMap.put( 0x00181402, new TagInfo(0x00181402, DicomElement.VR.CS, "Cassette Orientation") );
      tagInfoMap.put( 0x00181403, new TagInfo(0x00181403, DicomElement.VR.CS, "Cassette Size") );
      tagInfoMap.put( 0x00181404, new TagInfo(0x00181404, DicomElement.VR.US, "Exposures on Plate") );
      tagInfoMap.put( 0x00181405, new TagInfo(0x00181405, DicomElement.VR.IS, "Relative X-ray Exposure") );
      tagInfoMap.put( 0x00184000, new TagInfo(0x00184000, DicomElement.VR.SH, "Group 0018 Comments (RET)") );
      tagInfoMap.put( 0x00185000, new TagInfo(0x00185000, DicomElement.VR.SH, "Output Power") );
      tagInfoMap.put( 0x00185010, new TagInfo(0x00185010, DicomElement.VR.LO, "Transducer Data") );
      tagInfoMap.put( 0x00185012, new TagInfo(0x00185012, DicomElement.VR.DS, "Focus Depth") );
      tagInfoMap.put( 0x00185020, new TagInfo(0x00185020, DicomElement.VR.LO, "Preprocessing Function") );
      tagInfoMap.put( 0x00185021, new TagInfo(0x00185021, DicomElement.VR.LO, "Postprocessing Function") );
      tagInfoMap.put( 0x00185022, new TagInfo(0x00185022, DicomElement.VR.DS, "Mechanical Index") );
      tagInfoMap.put( 0x00185024, new TagInfo(0x00185024, DicomElement.VR.DS, "Thermal Index") );
      tagInfoMap.put( 0x00185026, new TagInfo(0x00185026, DicomElement.VR.DS, "Cranial Thermal Index") );
      tagInfoMap.put( 0x00185027, new TagInfo(0x00185027, DicomElement.VR.DS, "Soft Tissue Thermal Index") );
      tagInfoMap.put( 0x00185028, new TagInfo(0x00185028, DicomElement.VR.DS, "Soft Tissue-focus Thermal Index") );
      tagInfoMap.put( 0x00185029, new TagInfo(0x00185029, DicomElement.VR.DS, "Soft Tissue-surface Thermal Index") );
      tagInfoMap.put( 0x00185030, new TagInfo(0x00185030, DicomElement.VR.IS, "Dynamic Range (RET)") );
      tagInfoMap.put( 0x00185040, new TagInfo(0x00185040, DicomElement.VR.IS, "Total Gain (RET)") );
      tagInfoMap.put( 0x00185050, new TagInfo(0x00185050, DicomElement.VR.IS, "Depth of Scan Field") );
      tagInfoMap.put( 0x00185100, new TagInfo(0x00185100, DicomElement.VR.CS, "Patient Position") );
      tagInfoMap.put( 0x00185101, new TagInfo(0x00185101, DicomElement.VR.CS, "View Position") );
      tagInfoMap.put( 0x00185210, new TagInfo(0x00185210, DicomElement.VR.DS, "Image Transformation Matrix") );
      tagInfoMap.put( 0x00185212, new TagInfo(0x00185212, DicomElement.VR.DS, "Image Translation Vector") );
      tagInfoMap.put( 0x00186000, new TagInfo(0x00186000, DicomElement.VR.DS, "Sensitivity") );
      tagInfoMap.put( 0x00186011, new TagInfo(0x00186011, DicomElement.VR.SQ, "Sequence of Ultrasound Regions") );
      tagInfoMap.put( 0x00186012, new TagInfo(0x00186012, DicomElement.VR.US, "Region Spatial Format") );
      tagInfoMap.put( 0x00186014, new TagInfo(0x00186014, DicomElement.VR.US, "Region Data Type") );
      tagInfoMap.put( 0x00186016, new TagInfo(0x00186016, DicomElement.VR.UL, "Region Flags") );
      tagInfoMap.put( 0x00186018, new TagInfo(0x00186018, DicomElement.VR.UL, "Region Location Min X0") );
      tagInfoMap.put( 0x0018601A, new TagInfo(0x0018601A, DicomElement.VR.UL, "Region Location Min Y0") );
      tagInfoMap.put( 0x0018601C, new TagInfo(0x0018601C, DicomElement.VR.UL, "Region Location Max X1") );
      tagInfoMap.put( 0x0018601E, new TagInfo(0x0018601E, DicomElement.VR.UL, "Region Location Max Y1") );
      tagInfoMap.put( 0x00186020, new TagInfo(0x00186020, DicomElement.VR.SL, "Reference Pixel X0") );
      tagInfoMap.put( 0x00186022, new TagInfo(0x00186022, DicomElement.VR.SL, "Reference Pixel Y0") );
      tagInfoMap.put( 0x00186024, new TagInfo(0x00186024, DicomElement.VR.US, "Physical Units X Direction") );
      tagInfoMap.put( 0x00186026, new TagInfo(0x00186026, DicomElement.VR.US, "Physical Units Y Direction") );
      tagInfoMap.put( 0x00181628, new TagInfo(0x00181628, DicomElement.VR.FD, "Reference Pixel Physical Value X") );
      tagInfoMap.put( 0x0018602A, new TagInfo(0x0018602A, DicomElement.VR.FD, "Reference Pixel Physical Value Y") );
      tagInfoMap.put( 0x0018602C, new TagInfo(0x0018602C, DicomElement.VR.FD, "Physical Delta X") );
      tagInfoMap.put( 0x0018602E, new TagInfo(0x0018602E, DicomElement.VR.FD, "Physical Delta Y") );
      tagInfoMap.put( 0x00186030, new TagInfo(0x00186030, DicomElement.VR.UL, "Transducer Frequency") );
      tagInfoMap.put( 0x00186031, new TagInfo(0x00186031, DicomElement.VR.CS, "Transducer Type") );
      tagInfoMap.put( 0x00186032, new TagInfo(0x00186032, DicomElement.VR.UL, "Pulse Repetition Frequency") );
      tagInfoMap.put( 0x00186034, new TagInfo(0x00186034, DicomElement.VR.FD, "Doppler Correction Angle") );
      tagInfoMap.put( 0x00186036, new TagInfo(0x00186036, DicomElement.VR.FD, "Sterring Angle") );
      tagInfoMap.put( 0x00186038, new TagInfo(0x00186038, DicomElement.VR.UL, "Doppler Sample Volume X Position") );
      tagInfoMap.put( 0x0018603A, new TagInfo(0x0018603A, DicomElement.VR.UL, "Doppler Sample Volume Y Position") );
      tagInfoMap.put( 0x0018603C, new TagInfo(0x0018603C, DicomElement.VR.UL, "TM-Line Position X0") );
      tagInfoMap.put( 0x0018603E, new TagInfo(0x0018603E, DicomElement.VR.UL, "TM-Line Position Y0") );
      tagInfoMap.put( 0x00186040, new TagInfo(0x00186040, DicomElement.VR.UL, "TM-Line Position X1") );
      tagInfoMap.put( 0x00186042, new TagInfo(0x00186042, DicomElement.VR.UL, "TM-Line Position Y1") );
      tagInfoMap.put( 0x00186044, new TagInfo(0x00186044, DicomElement.VR.US, "Pixel Component Organization") );
      tagInfoMap.put( 0x00186046, new TagInfo(0x00186046, DicomElement.VR.UL, "Pixel Component Organization") );
      tagInfoMap.put( 0x00186048, new TagInfo(0x00186048, DicomElement.VR.UL, "Pixel Component Range Start") );
      tagInfoMap.put( 0x0018604A, new TagInfo(0x0018604A, DicomElement.VR.UL, "Pixel Component Range Stop") );
      tagInfoMap.put( 0x0018604C, new TagInfo(0x0018604C, DicomElement.VR.US, "Pixel Component Physical Units") );
      tagInfoMap.put( 0x0018604E, new TagInfo(0x0018604E, DicomElement.VR.US, "Pixel Component Data Type") );
      tagInfoMap.put( 0x00186050, new TagInfo(0x00186050, DicomElement.VR.UL, "Number of Table Break Points") );
      tagInfoMap.put( 0x00186052, new TagInfo(0x00186052, DicomElement.VR.UL, "Table of X Break Points") );
      tagInfoMap.put( 0x00186054, new TagInfo(0x00186054, DicomElement.VR.FD, "Table of Y Break Points") );
      tagInfoMap.put( 0x00200000, new TagInfo(0x00200000, DicomElement.VR.UL, "Group 0020 Length") );
      tagInfoMap.put( 0x0020000D, new TagInfo(0x0020000D, DicomElement.VR.UI, "Study Instance UID") );
      tagInfoMap.put( 0x0020000E, new TagInfo(0x0020000E, DicomElement.VR.UI, "Series Instance UID") );
      tagInfoMap.put( 0x00200010, new TagInfo(0x00200010, DicomElement.VR.SH, "Study ID") );
      tagInfoMap.put( 0x00200011, new TagInfo(0x00200011, DicomElement.VR.IS, "Series Number") );
      tagInfoMap.put( 0x00200012, new TagInfo(0x00200012, DicomElement.VR.IS, "Acquisition Number") );
      tagInfoMap.put( 0x00200013, new TagInfo(0x00200013, DicomElement.VR.IS, "Image Number") );
      tagInfoMap.put( 0x00200014, new TagInfo(0x00200014, DicomElement.VR.IS, "Isotope Number") );
      tagInfoMap.put( 0x00200015, new TagInfo(0x00200015, DicomElement.VR.IS, "Phase Number") );
      tagInfoMap.put( 0x00200016, new TagInfo(0x00200016, DicomElement.VR.IS, "Interval Number") );
      tagInfoMap.put( 0x00200017, new TagInfo(0x00200017, DicomElement.VR.IS, "Time Slot Number") );
      tagInfoMap.put( 0x00200018, new TagInfo(0x00200018, DicomElement.VR.IS, "Angle Number") );
      tagInfoMap.put( 0x00200020, new TagInfo(0x00200020, DicomElement.VR.CS, "Patient Orientation") );
      tagInfoMap.put( 0x00200022, new TagInfo(0x00200022, DicomElement.VR.US, "Overlay Number") );
      tagInfoMap.put( 0x00200024, new TagInfo(0x00200024, DicomElement.VR.US, "Curve Number") );
      tagInfoMap.put( 0x00200030, new TagInfo(0x00200030, DicomElement.VR.DS, "Image Position (RET)") );
      tagInfoMap.put( 0x00200032, new TagInfo(0x00200032, DicomElement.VR.DS, "Image Position (Patient)") );
      tagInfoMap.put( 0x00200035, new TagInfo(0x00200035, DicomElement.VR.DS, "Image Orientation (RET)") );
      tagInfoMap.put( 0x00200037, new TagInfo(0x00200037, DicomElement.VR.DS, "Image Orientation (Patient)") );
      tagInfoMap.put( 0x00200050, new TagInfo(0x00200050, DicomElement.VR.DS, "Location (RET)") );
      tagInfoMap.put( 0x00200052, new TagInfo(0x00200052, DicomElement.VR.UI, "Frame of Reference UID") );
      tagInfoMap.put( 0x00200060, new TagInfo(0x00200060, DicomElement.VR.CS, "Laterality") );
      tagInfoMap.put( 0x00200070, new TagInfo(0x00200070, DicomElement.VR.SH, "Image Geometry Type (RET)") );
      tagInfoMap.put( 0x00200080, new TagInfo(0x00200080, DicomElement.VR.UI, "Masking Image UID") );
      tagInfoMap.put( 0x00200100, new TagInfo(0x00200100, DicomElement.VR.IS, "Temporal Position Identifier") );
      tagInfoMap.put( 0x00200105, new TagInfo(0x00200105, DicomElement.VR.IS, "Number of Temporal Positions") );
      tagInfoMap.put( 0x00200110, new TagInfo(0x00200110, DicomElement.VR.DS, "Temporal Resolution") );
      tagInfoMap.put( 0x00201000, new TagInfo(0x00201000, DicomElement.VR.IS, "Series in Study") );
      tagInfoMap.put( 0x00201001, new TagInfo(0x00201001, DicomElement.VR.IS, "Acquisitions in Series (RET)") );
      tagInfoMap.put( 0x00201002, new TagInfo(0x00201002, DicomElement.VR.IS, "Images in Acquisition") );
      tagInfoMap.put( 0x00201004, new TagInfo(0x00201004, DicomElement.VR.IS, "Acquisition in Study") );
      tagInfoMap.put( 0x00201020, new TagInfo(0x00201020, DicomElement.VR.SH, "Reference (RET)") );
      tagInfoMap.put( 0x00201040, new TagInfo(0x00201040, DicomElement.VR.LO, "Position Reference Indicator") );
      tagInfoMap.put( 0x00201041, new TagInfo(0x00201041, DicomElement.VR.DS, "Slice Location") );
      tagInfoMap.put( 0x00201070, new TagInfo(0x00201070, DicomElement.VR.IS, "Other Study Numbers") );
      tagInfoMap.put( 0x00201200, new TagInfo(0x00201200, DicomElement.VR.IS, "Number of Patient Related Studies") );
      tagInfoMap.put( 0x00201202, new TagInfo(0x00201202, DicomElement.VR.IS, "Number of Patient Related Series") );
      tagInfoMap.put( 0x00201204, new TagInfo(0x00201204, DicomElement.VR.IS, "Number of Patient Related Images") );
      tagInfoMap.put( 0x00201206, new TagInfo(0x00201206, DicomElement.VR.IS, "Number of Study Related Series") );
      tagInfoMap.put( 0x00201208, new TagInfo(0x00201208, DicomElement.VR.IS, "Number of Study Related Images") );
      tagInfoMap.put( 0x00203100, new TagInfo(0x00203100, DicomElement.VR.SH, "Source Image ID (RET)s") );
      tagInfoMap.put( 0x00203401, new TagInfo(0x00203401, DicomElement.VR.SH, "Modifying Device ID (RET)") );
      tagInfoMap.put( 0x00203402, new TagInfo(0x00203402, DicomElement.VR.SH, "Modified Image ID (RET)") );
      tagInfoMap.put( 0x00203403, new TagInfo(0x00203403, DicomElement.VR.SH, "Modified Image Date (RET)") );
      tagInfoMap.put( 0x00203404, new TagInfo(0x00203404, DicomElement.VR.SH, "Modifying Device Manufacturer (RET)") );
      tagInfoMap.put( 0x00203405, new TagInfo(0x00203405, DicomElement.VR.SH, "Modified Image Time (RET)") );
      tagInfoMap.put( 0x00203406, new TagInfo(0x00203406, DicomElement.VR.SH, "Modified Image Description (RET)") );
      tagInfoMap.put( 0x00204000, new TagInfo(0x00204000, DicomElement.VR.LT, "Image Comments") );
      tagInfoMap.put( 0x00205000, new TagInfo(0x00205000, DicomElement.VR.US, "Original Image Identification (RET)") );
      tagInfoMap.put( 0x00205002, new TagInfo(0x00205002, DicomElement.VR.SH, "Original Image Identification Nomenclature (RET)") );
      tagInfoMap.put( 0x00280000, new TagInfo(0x00280000, DicomElement.VR.UL, "Group 0028 Length") );
      tagInfoMap.put( 0x00280002, new TagInfo(0x00280002, DicomElement.VR.US, "Samples per Pixel") );
      tagInfoMap.put( 0x00280004, new TagInfo(0x00280004, DicomElement.VR.CS, "Photometric Interpretation") );
      tagInfoMap.put( 0x00280005, new TagInfo(0x00280005, DicomElement.VR.US, "Image Dimensions (RET)") );
      tagInfoMap.put( 0x00280006, new TagInfo(0x00280006, DicomElement.VR.US, "Planar Configuration") );
      tagInfoMap.put( 0x00280008, new TagInfo(0x00280008, DicomElement.VR.IS, "Number of Frames") );
      tagInfoMap.put( 0x00280009, new TagInfo(0x00280009, DicomElement.VR.AT, "Frame Increment Pointer") );
      tagInfoMap.put( 0x00280010, new TagInfo(0x00280010, DicomElement.VR.US, "Rows") );
      tagInfoMap.put( 0x00280011, new TagInfo(0x00280011, DicomElement.VR.US, "Columns") );
      tagInfoMap.put( 0x00280030, new TagInfo(0x00280030, DicomElement.VR.DS, "Pixel Spacing") );
      tagInfoMap.put( 0x00280031, new TagInfo(0x00280031, DicomElement.VR.DS, "Zoom Factor") );
      tagInfoMap.put( 0x00280032, new TagInfo(0x00280032, DicomElement.VR.DS, "Zoom Center") );
      tagInfoMap.put( 0x00280034, new TagInfo(0x00280034, DicomElement.VR.IS, "Pixel Aspect Ratio") );
      tagInfoMap.put( 0x00280040, new TagInfo(0x00280040, DicomElement.VR.SH, "Image Format (RET)") );
      tagInfoMap.put( 0x00280050, new TagInfo(0x00280050, DicomElement.VR.SH, "Manipulated Image (RET)") );
      tagInfoMap.put( 0x00280051, new TagInfo(0x00280051, DicomElement.VR.CS, "Corrected Image") );
      tagInfoMap.put( 0x00280060, new TagInfo(0x00280060, DicomElement.VR.SH, "Compression Code (RET)") );
      tagInfoMap.put( 0x00280100, new TagInfo(0x00280100, DicomElement.VR.US, "Bits Allocated") );
      tagInfoMap.put( 0x00280101, new TagInfo(0x00280101, DicomElement.VR.US, "Bits Stored") );
      tagInfoMap.put( 0x00280102, new TagInfo(0x00280102, DicomElement.VR.US, "High Bit") );
      tagInfoMap.put( 0x00280103, new TagInfo(0x00280103, DicomElement.VR.US, "Pixel Representation") );
      tagInfoMap.put( 0x00280104, new TagInfo(0x00280104, DicomElement.VR.US, "Smallest Valid Pixel Value (RET)") );
      tagInfoMap.put( 0x00280105, new TagInfo(0x00280105, DicomElement.VR.US, "Largest Valid Pixel Value (RET)") );
      tagInfoMap.put( 0x00280106, new TagInfo(0x00280106, DicomElement.VR.US, "Smallest Image Pixel Value") );
      tagInfoMap.put( 0x00280107, new TagInfo(0x00280107, DicomElement.VR.US, "Largest Image Pixel Value") );
      tagInfoMap.put( 0x00280108, new TagInfo(0x00280108, DicomElement.VR.US, "Smallest Pixel Value in Series") );
      tagInfoMap.put( 0x00280109, new TagInfo(0x00280109, DicomElement.VR.US, "Largest Pixel Value in Series") );
      tagInfoMap.put( 0x00280120, new TagInfo(0x00280120, DicomElement.VR.US, "Pixel Padding Value") );
      tagInfoMap.put( 0x00280200, new TagInfo(0x00280200, DicomElement.VR.US, "Image Location (RET)") );
      tagInfoMap.put( 0x00281050, new TagInfo(0x00281050, DicomElement.VR.DS, "Window Center") );
      tagInfoMap.put( 0x00281051, new TagInfo(0x00281051, DicomElement.VR.DS, "Window Width") );
      tagInfoMap.put( 0x00281052, new TagInfo(0x00281052, DicomElement.VR.DS, "Rescale Intercept") );
      tagInfoMap.put( 0x00281053, new TagInfo(0x00281053, DicomElement.VR.DS, "Rescale Slope") );
      tagInfoMap.put( 0x00281054, new TagInfo(0x00281054, DicomElement.VR.LO, "Rescale Type") );
      tagInfoMap.put( 0x00281055, new TagInfo(0x00281055, DicomElement.VR.LO, "Window Center & Width Explanation") );
      tagInfoMap.put( 0x00281080, new TagInfo(0x00281080, DicomElement.VR.SH, "Gray Scale (RET)") );
      tagInfoMap.put( 0x00281100, new TagInfo(0x00281100, DicomElement.VR.US, "Gray Lookup Table Descriptor (RET)") );
      tagInfoMap.put( 0x00281101, new TagInfo(0x00281101, DicomElement.VR.US, "Red Palette Color Lookup Table Descriptor") );
      tagInfoMap.put( 0x00281102, new TagInfo(0x00281102, DicomElement.VR.US, "Green Palette Color Lookup Table Descriptor") );
      tagInfoMap.put( 0x00281103, new TagInfo(0x00281103, DicomElement.VR.US, "Blue Palette Color Lookup Table Descriptor") );
      tagInfoMap.put( 0x00281200, new TagInfo(0x00281200, DicomElement.VR.US, "Gray Lookup Table Data (RET)") );
      tagInfoMap.put( 0x00281201, new TagInfo(0x00281201, DicomElement.VR.US, "Red Palette Color Lookup Table Data") );
      tagInfoMap.put( 0x00281202, new TagInfo(0x00281202, DicomElement.VR.US, "Green Palette Color Lookup Table Data") );
      tagInfoMap.put( 0x00281203, new TagInfo(0x00281203, DicomElement.VR.US, "Blue Palette Color Lookup Table Data") );
      tagInfoMap.put( 0x00282110, new TagInfo(0x00281203, DicomElement.VR.CS, "Lossy Image Compression") );
      tagInfoMap.put( 0x00282112, new TagInfo(0x00281203, DicomElement.VR.DS, "Lossy Image Compression Ratio") );
      tagInfoMap.put( 0x00282114, new TagInfo(0x00281203, DicomElement.VR.CS, "Lossy Image Compression Method") );
      tagInfoMap.put( 0x00283000, new TagInfo(0x00283000, DicomElement.VR.SQ, "Modality LUT Sequence") );
      tagInfoMap.put( 0x00283002, new TagInfo(0x00283002, DicomElement.VR.US, "LUT Descriptor") );
      tagInfoMap.put( 0x00283003, new TagInfo(0x00283003, DicomElement.VR.LO, "LUT Explanation") );
      tagInfoMap.put( 0x00283004, new TagInfo(0x00283004, DicomElement.VR.LO, "Madality LUT Type") );
      tagInfoMap.put( 0x00283006, new TagInfo(0x00283006, DicomElement.VR.US, "LUT Data") );
      tagInfoMap.put( 0x00283010, new TagInfo(0x00283010, DicomElement.VR.SQ, "VOI LUT Sequence") );
      tagInfoMap.put( 0x00284000, new TagInfo(0x00284000, DicomElement.VR.SH, "Group 0028 Comments (RET)") );
      tagInfoMap.put( 0x00320000, new TagInfo(0x00320000, DicomElement.VR.UL, "Group 0032 Length") );
      tagInfoMap.put( 0x0032000A, new TagInfo(0x0032000A, DicomElement.VR.CS, "Study Status ID") );
      tagInfoMap.put( 0x0032000C, new TagInfo(0x0032000C, DicomElement.VR.CS, "Study Priority ID") );
      tagInfoMap.put( 0x00320012, new TagInfo(0x00320012, DicomElement.VR.LO, "Study ID Issuer") );
      tagInfoMap.put( 0x00320032, new TagInfo(0x00320032, DicomElement.VR.DA, "Study Verified Date") );
      tagInfoMap.put( 0x00320033, new TagInfo(0x00320033, DicomElement.VR.TM, "Study Verified Time") );
      tagInfoMap.put( 0x00320034, new TagInfo(0x00320034, DicomElement.VR.DA, "Study Read Date") );
      tagInfoMap.put( 0x00320035, new TagInfo(0x00320035, DicomElement.VR.TM, "Study Read Time") );
      tagInfoMap.put( 0x00321000, new TagInfo(0x00321000, DicomElement.VR.DA, "Scheduled Study Start Date") );
      tagInfoMap.put( 0x00321001, new TagInfo(0x00321001, DicomElement.VR.TM, "Scheduled Study Start Time") );
      tagInfoMap.put( 0x00321010, new TagInfo(0x00321010, DicomElement.VR.DA, "Scheduled Study Stop Date") );
      tagInfoMap.put( 0x00321011, new TagInfo(0x00321011, DicomElement.VR.TM, "Scheduled Study Stop Time") );
      tagInfoMap.put( 0x00321020, new TagInfo(0x00321020, DicomElement.VR.LO, "Scheduled Study Location") );
      tagInfoMap.put( 0x00321021, new TagInfo(0x00321021, DicomElement.VR.AE, "Scheduled Study Location AE Title(s)") );
      tagInfoMap.put( 0x00321030, new TagInfo(0x00321030, DicomElement.VR.LO, "Reason  for Study") );
      tagInfoMap.put( 0x00321032, new TagInfo(0x00321032, DicomElement.VR.PN, "Requesting Physician") );
      tagInfoMap.put( 0x00321033, new TagInfo(0x00321033, DicomElement.VR.LO, "Requesting Service") );
      tagInfoMap.put( 0x00321040, new TagInfo(0x00321040, DicomElement.VR.DA, "Study Arrival Date") );
      tagInfoMap.put( 0x00321041, new TagInfo(0x00321041, DicomElement.VR.TM, "Study Arrival Time") );
      tagInfoMap.put( 0x00321050, new TagInfo(0x00321050, DicomElement.VR.DA, "Study Completion Date") );
      tagInfoMap.put( 0x00321051, new TagInfo(0x00321051, DicomElement.VR.TM, "Study Completion Time") );
      tagInfoMap.put( 0x00321055, new TagInfo(0x00321055, DicomElement.VR.CS, "Study Component Status ID") );
      tagInfoMap.put( 0x00321060, new TagInfo(0x00321060, DicomElement.VR.LO, "Requested Procedure Description") );
      tagInfoMap.put( 0x00321064, new TagInfo(0x00321064, DicomElement.VR.SQ, "Requested Procedure Code Sequence") );
      tagInfoMap.put( 0x00321070, new TagInfo(0x00321070, DicomElement.VR.LO, "Requested Contrast Agent") );
      tagInfoMap.put( 0x00324000, new TagInfo(0x00324000, DicomElement.VR.LT, "Study Comments") );
      tagInfoMap.put( 0x00380000, new TagInfo(0x00380000, DicomElement.VR.UL, "Group 0038 Length") );
      tagInfoMap.put( 0x00380004, new TagInfo(0x00380004, DicomElement.VR.SQ, "Referenced Patient Alias Sequence") );
      tagInfoMap.put( 0x00380008, new TagInfo(0x00380008, DicomElement.VR.CS, "Visit Status ID") );
      tagInfoMap.put( 0x00380010, new TagInfo(0x00380010, DicomElement.VR.LO, "Admissin ID") );
      tagInfoMap.put( 0x00380011, new TagInfo(0x00380011, DicomElement.VR.LO, "Issuer of Admission ID") );
      tagInfoMap.put( 0x00380016, new TagInfo(0x00380016, DicomElement.VR.LO, "Route of Admissions") );
      tagInfoMap.put( 0x0038001A, new TagInfo(0x0038001A, DicomElement.VR.DA, "Scheduled Admissin Date") );
      tagInfoMap.put( 0x0038001B, new TagInfo(0x0038001B, DicomElement.VR.TM, "Scheduled Adission Time") );
      tagInfoMap.put( 0x0038001C, new TagInfo(0x0038001C, DicomElement.VR.DA, "Scheduled Discharge Date") );
      tagInfoMap.put( 0x0038001D, new TagInfo(0x0038001D, DicomElement.VR.TM, "Scheduled Discharge Time") );
      tagInfoMap.put( 0x0038001E, new TagInfo(0x0038001E, DicomElement.VR.LO, "Scheduled Patient Institution Residence") );
      tagInfoMap.put( 0x00380020, new TagInfo(0x00380020, DicomElement.VR.DA, "Admitting Date") );
      tagInfoMap.put( 0x00380021, new TagInfo(0x00380021, DicomElement.VR.TM, "Admitting Time") );
      tagInfoMap.put( 0x00380030, new TagInfo(0x00380030, DicomElement.VR.DA, "Discharge Date") );
      tagInfoMap.put( 0x00380032, new TagInfo(0x00380032, DicomElement.VR.TM, "Discharge Time") );
      tagInfoMap.put( 0x00380040, new TagInfo(0x00380040, DicomElement.VR.LO, "Discharge Diagnosis Description") );
      tagInfoMap.put( 0x00380044, new TagInfo(0x00380044, DicomElement.VR.SQ, "Discharge Diagnosis Code Sequence") );
      tagInfoMap.put( 0x00380050, new TagInfo(0x00380050, DicomElement.VR.LO, "Special Needs") );
      tagInfoMap.put( 0x00380300, new TagInfo(0x00380300, DicomElement.VR.LO, "Current Patient Location") );
      tagInfoMap.put( 0x00380400, new TagInfo(0x00380400, DicomElement.VR.LO, "Patient's Institution Residence") );
      tagInfoMap.put( 0x00380500, new TagInfo(0x00380500, DicomElement.VR.LO, "Patient State") );
      tagInfoMap.put( 0x00384000, new TagInfo(0x00384000, DicomElement.VR.LT, "Visit Comments") );
      tagInfoMap.put( 0x00400275, new TagInfo(0x00400275, DicomElement.VR.SQ, "Request Attributes Sequence") );
      tagInfoMap.put( 0x00880000, new TagInfo(0x00880000, DicomElement.VR.UL, "Group 0088 Length") );
      tagInfoMap.put( 0x00880130, new TagInfo(0x00880130, DicomElement.VR.SH, "Storage Media File-set ID") );
      tagInfoMap.put( 0x00880140, new TagInfo(0x00880140, DicomElement.VR.UI, "Storage Media File-set UID") );
      tagInfoMap.put( 0x20000000, new TagInfo(0x20000000, DicomElement.VR.UL, "Group 2000 Length") );
      tagInfoMap.put( 0x20000010, new TagInfo(0x20000010, DicomElement.VR.IS, "Number of Copies") );
      tagInfoMap.put( 0x20000020, new TagInfo(0x20000020, DicomElement.VR.CS, "Print Priority") );
      tagInfoMap.put( 0x20000030, new TagInfo(0x20000030, DicomElement.VR.CS, "Medium Type") );
      tagInfoMap.put( 0x20000040, new TagInfo(0x20000040, DicomElement.VR.CS, "Film Destination") );
      tagInfoMap.put( 0x20000050, new TagInfo(0x20000050, DicomElement.VR.LO, "Film Session Label") );
      tagInfoMap.put( 0x20000060, new TagInfo(0x20000060, DicomElement.VR.IS, "Memory Allocation") );
      tagInfoMap.put( 0x20000500, new TagInfo(0x20000500, DicomElement.VR.SQ, "Referenced Film Box Sequence") );
      tagInfoMap.put( 0x20100000, new TagInfo(0x20100000, DicomElement.VR.UL, "Group 2010 Length") );
      tagInfoMap.put( 0x20100010, new TagInfo(0x20100010, DicomElement.VR.ST, "Image Display Format") );
      tagInfoMap.put( 0x20100030, new TagInfo(0x20100030, DicomElement.VR.CS, "Annotation Display Format ID") );
      tagInfoMap.put( 0x20100040, new TagInfo(0x20100040, DicomElement.VR.CS, "Film Orientation") );
      tagInfoMap.put( 0x20100050, new TagInfo(0x20100050, DicomElement.VR.CS, "Film Size ID") );
      tagInfoMap.put( 0x20100060, new TagInfo(0x20100060, DicomElement.VR.CS, "Magnification Type") );
      tagInfoMap.put( 0x20100080, new TagInfo(0x20100080, DicomElement.VR.CS, "Smoothing Type") );
      tagInfoMap.put( 0x20100100, new TagInfo(0x20100100, DicomElement.VR.CS, "Border Density") );
      tagInfoMap.put( 0x20100110, new TagInfo(0x20100110, DicomElement.VR.CS, "Empty Image Density") );
      tagInfoMap.put( 0x20100120, new TagInfo(0x20100120, DicomElement.VR.US, "Min Density") );
      tagInfoMap.put( 0x20100130, new TagInfo(0x20100130, DicomElement.VR.US, "Max Density") );
      tagInfoMap.put( 0x20100140, new TagInfo(0x20100140, DicomElement.VR.CS, "Trim") );
      tagInfoMap.put( 0x20100150, new TagInfo(0x20100150, DicomElement.VR.ST, "Configuration Information") );
      tagInfoMap.put( 0x20100500, new TagInfo(0x20100500, DicomElement.VR.SQ, "Referenced Film Session Sequence") );
      tagInfoMap.put( 0x20100510, new TagInfo(0x20100510, DicomElement.VR.SQ, "Referenced Basic Image Box Sequence") );
      tagInfoMap.put( 0x20100520, new TagInfo(0x20100520, DicomElement.VR.SQ, "Referenced Basic Annotation Box Sequence") );
      tagInfoMap.put( 0x20200000, new TagInfo(0x20200000, DicomElement.VR.UL, "Group 2020 Length") );
      tagInfoMap.put( 0x20200010, new TagInfo(0x20200010, DicomElement.VR.US, "Image Position") );
      tagInfoMap.put( 0x20200020, new TagInfo(0x20200020, DicomElement.VR.CS, "Polarity") );
      tagInfoMap.put( 0x20200030, new TagInfo(0x20200030, DicomElement.VR.DS, "Requested Image Size") );
      tagInfoMap.put( 0x20200110, new TagInfo(0x20200110, DicomElement.VR.SQ, "Preformatted Greyscale Image Sequence") );
      tagInfoMap.put( 0x20200111, new TagInfo(0x20200111, DicomElement.VR.SQ, "Preformatted Color Image Sequence") );
      tagInfoMap.put( 0x20200130, new TagInfo(0x20200130, DicomElement.VR.SQ, "Referenced Image Overlay Box Sequence") );
      tagInfoMap.put( 0x20200140, new TagInfo(0x20200140, DicomElement.VR.SQ, "Referenced VOI LUT Sequence") );
      tagInfoMap.put( 0x20300000, new TagInfo(0x20300000, DicomElement.VR.UL, "Group 2030 Length") );
      tagInfoMap.put( 0x20300010, new TagInfo(0x20300010, DicomElement.VR.US, "Annotation Position") );
      tagInfoMap.put( 0x20300020, new TagInfo(0x20300020, DicomElement.VR.LO, "Text String") );
      tagInfoMap.put( 0x20400000, new TagInfo(0x20400000, DicomElement.VR.UL, "Group 2040 Length") );
      tagInfoMap.put( 0x20400010, new TagInfo(0x20400010, DicomElement.VR.SQ, "Referenced Overlay Plane Sequence") );
      tagInfoMap.put( 0x20400011, new TagInfo(0x20400011, DicomElement.VR.US, "Refenced Overlay Plane Groups") );
      tagInfoMap.put( 0x20400060, new TagInfo(0x20400060, DicomElement.VR.CS, "Overlay Magnification Type") );
      tagInfoMap.put( 0x20400070, new TagInfo(0x20400070, DicomElement.VR.CS, "Overlay Smoothing Type") );
      tagInfoMap.put( 0x20400080, new TagInfo(0x20400080, DicomElement.VR.CS, "Overlay Foreground Density") );
      tagInfoMap.put( 0x20400090, new TagInfo(0x20400090, DicomElement.VR.CS, "overlay Mode") );
      tagInfoMap.put( 0x20400100, new TagInfo(0x20400100, DicomElement.VR.CS, "Threshold Density") );
      tagInfoMap.put( 0x20400500, new TagInfo(0x20400500, DicomElement.VR.SQ, "Referenced Image Box Sequence") );
      tagInfoMap.put( 0x21000000, new TagInfo(0x21000000, DicomElement.VR.UL, "Group 2100 Length") );
      tagInfoMap.put( 0x21000020, new TagInfo(0x21000020, DicomElement.VR.CS, "Execution Status") );
      tagInfoMap.put( 0x21000030, new TagInfo(0x21000030, DicomElement.VR.CS, "Execution Status Info") );
      tagInfoMap.put( 0x21000040, new TagInfo(0x21000040, DicomElement.VR.DA, "Creation Date") );
      tagInfoMap.put( 0x21000050, new TagInfo(0x21000050, DicomElement.VR.TM, "Creation Time") );
      tagInfoMap.put( 0x21000070, new TagInfo(0x21000070, DicomElement.VR.AE, "Originator") );
      tagInfoMap.put( 0x21000500, new TagInfo(0x21000500, DicomElement.VR.SQ, "Referenced Print Job Sequence") );
      tagInfoMap.put( 0x21100000, new TagInfo(0x21100000, DicomElement.VR.UL, "Group 2110 Length") );
      tagInfoMap.put( 0x21100010, new TagInfo(0x21100010, DicomElement.VR.CS, "Printer Status") );
      tagInfoMap.put( 0x21100020, new TagInfo(0x21100020, DicomElement.VR.CS, "Printer Status Info") );
      tagInfoMap.put( 0x21100030, new TagInfo(0x21100030, DicomElement.VR.ST, "Printer Name") );
      tagInfoMap.put( 0x40000000, new TagInfo(0x40000000, DicomElement.VR.UL, "Group 4000 Length (RET)") );
      tagInfoMap.put( 0x40000010, new TagInfo(0x40000010, DicomElement.VR.SH, "Arbitray (RET)") );
      tagInfoMap.put( 0x40004000, new TagInfo(0x40004000, DicomElement.VR.LT, "Group 4000 Comments (RET)") );
      tagInfoMap.put( 0x40080000, new TagInfo(0x40080000, DicomElement.VR.UL, "Group 4008 Length") );
      tagInfoMap.put( 0x40080040, new TagInfo(0x40080040, DicomElement.VR.SH, "Results ID") );
      tagInfoMap.put( 0x40080042, new TagInfo(0x40080042, DicomElement.VR.LO, "Results ID Issuer") );
      tagInfoMap.put( 0x40080050, new TagInfo(0x40080050, DicomElement.VR.SQ, "Referenced Interpretation Sequence") );
      tagInfoMap.put( 0x40080100, new TagInfo(0x40080100, DicomElement.VR.DA, "Interpretation Recorded Date") );
      tagInfoMap.put( 0x40080101, new TagInfo(0x40080101, DicomElement.VR.TM, "Interpretation Recorded Time") );
      tagInfoMap.put( 0x40080102, new TagInfo(0x40080102, DicomElement.VR.PN, "Interpretation Recorder") );
      tagInfoMap.put( 0x40080103, new TagInfo(0x40080103, DicomElement.VR.LO, "Reference to Recorded Sound") );
      tagInfoMap.put( 0x40080108, new TagInfo(0x40080108, DicomElement.VR.DA, "Interpretation Transcription Time") );
      tagInfoMap.put( 0x40080109, new TagInfo(0x40080109, DicomElement.VR.TM, "Interpretation Transcription Time") );
      tagInfoMap.put( 0x4008010A, new TagInfo(0x4008010A, DicomElement.VR.PN, "Interpretation Transcriber") );
      tagInfoMap.put( 0x4008010B, new TagInfo(0x4008010B, DicomElement.VR.ST, "Interpretation Text") );
      tagInfoMap.put( 0x4008010C, new TagInfo(0x4008010C, DicomElement.VR.PN, "Interpretation Author") );
      tagInfoMap.put( 0x40080111, new TagInfo(0x40080111, DicomElement.VR.SQ, "Interpretation Approver Sequence") );
      tagInfoMap.put( 0x40080112, new TagInfo(0x40080112, DicomElement.VR.DA, "Interpretation Approval Date") );
      tagInfoMap.put( 0x40080113, new TagInfo(0x40080113, DicomElement.VR.TM, "Interpretation Approval Time") );
      tagInfoMap.put( 0x40080114, new TagInfo(0x40080114, DicomElement.VR.PN, "Physician Approving Interpretation") );
      tagInfoMap.put( 0x40080115, new TagInfo(0x40080115, DicomElement.VR.LT, "Interpretation Diagnosis Description") );
      tagInfoMap.put( 0x40080117, new TagInfo(0x40080117, DicomElement.VR.SQ, "Diagnosis Code Sequence") );
      tagInfoMap.put( 0x40080118, new TagInfo(0x40080118, DicomElement.VR.SQ, "Results Distribution List Sequence") );
      tagInfoMap.put( 0x40080119, new TagInfo(0x40080119, DicomElement.VR.PN, "Distribution Name") );
      tagInfoMap.put( 0x4008011A, new TagInfo(0x4008011A, DicomElement.VR.LO, "Distribution Address") );
      tagInfoMap.put( 0x40080200, new TagInfo(0x40080200, DicomElement.VR.SH, "Interpretation ID") );
      tagInfoMap.put( 0x40080202, new TagInfo(0x40080202, DicomElement.VR.LO, "Interpretation ID Issuer") );
      tagInfoMap.put( 0x40080210, new TagInfo(0x40080210, DicomElement.VR.CS, "Interpretation Type ID") );
      tagInfoMap.put( 0x40080212, new TagInfo(0x40080212, DicomElement.VR.CS, "Interpretation Status ID") );
      tagInfoMap.put( 0x40080300, new TagInfo(0x40080300, DicomElement.VR.ST, "Impression") );
      tagInfoMap.put( 0x40084000, new TagInfo(0x40084000, DicomElement.VR.SH, "Group 4008 Comments") );
      tagInfoMap.put( 0x50000000, new TagInfo(0x50000000, DicomElement.VR.UL, "Group 5000 Length") );
      tagInfoMap.put( 0x50000005, new TagInfo(0x50000005, DicomElement.VR.US, "Curve Dimensions") );
      tagInfoMap.put( 0x50000010, new TagInfo(0x50000010, DicomElement.VR.US, "Number of Points") );
      tagInfoMap.put( 0x50000020, new TagInfo(0x50000020, DicomElement.VR.CS, "Type of Data") );
      tagInfoMap.put( 0x50000022, new TagInfo(0x50000022, DicomElement.VR.LO, "Curve Description") );
      tagInfoMap.put( 0x50000030, new TagInfo(0x50000030, DicomElement.VR.SH, "Axis Units") );
      tagInfoMap.put( 0x50000040, new TagInfo(0x50000040, DicomElement.VR.SH, "Axis Labels") );
      tagInfoMap.put( 0x50000103, new TagInfo(0x50000103, DicomElement.VR.US, "Data Value Representation") );
      tagInfoMap.put( 0x50000104, new TagInfo(0x50000104, DicomElement.VR.US, "Minimum Coordinate Value") );
      tagInfoMap.put( 0x50000105, new TagInfo(0x50000105, DicomElement.VR.US, "Maximum Coordinate Value") );
      tagInfoMap.put( 0x50000106, new TagInfo(0x50000106, DicomElement.VR.SH, "Curve Range") );
      tagInfoMap.put( 0x50000110, new TagInfo(0x50000110, DicomElement.VR.US, "Curve Data Descriptor") );
      tagInfoMap.put( 0x50000112, new TagInfo(0x50000112, DicomElement.VR.US, "Coordinate Start Value") );
      tagInfoMap.put( 0x50000114, new TagInfo(0x50000114, DicomElement.VR.US, "Coordinate Step Value") );
      tagInfoMap.put( 0x50002000, new TagInfo(0x50002000, DicomElement.VR.US, "Audio Type") );
      tagInfoMap.put( 0x50002002, new TagInfo(0x50002002, DicomElement.VR.US, "Audio Sample Format") );
      tagInfoMap.put( 0x50002004, new TagInfo(0x50002004, DicomElement.VR.US, "Number of Channels") );
      tagInfoMap.put( 0x50002006, new TagInfo(0x50002006, DicomElement.VR.UL, "Number of Samples") );
      tagInfoMap.put( 0x50002008, new TagInfo(0x50002008, DicomElement.VR.UL, "Sample Rate") );
      tagInfoMap.put( 0x5000200A, new TagInfo(0x5000200A, DicomElement.VR.UL, "Total Time") );
      tagInfoMap.put( 0x5000200C, new TagInfo(0x5000200C, DicomElement.VR.OX, "Audio Sample Data") );
      tagInfoMap.put( 0x5000200E, new TagInfo(0x5000200E, DicomElement.VR.LT, "Audio Comments") );
      tagInfoMap.put( 0x50003000, new TagInfo(0x50003000, DicomElement.VR.OX, "Curve Data") );
      tagInfoMap.put( 0x60000000, new TagInfo(0x60000000, DicomElement.VR.UL, "Group 6000 Length") );
      tagInfoMap.put( 0x60000010, new TagInfo(0x60000010, DicomElement.VR.US, "Rows") );
      tagInfoMap.put( 0x60000011, new TagInfo(0x60000011, DicomElement.VR.US, "Columns") );
      tagInfoMap.put( 0x60000015, new TagInfo(0x60000015, DicomElement.VR.IS, "Number of Frames in Overlay") );
      tagInfoMap.put( 0x60000040, new TagInfo(0x60000040, DicomElement.VR.CS, "Overlay Type") );
      tagInfoMap.put( 0x60000050, new TagInfo(0x60000050, DicomElement.VR.SS, "Origin") );
      tagInfoMap.put( 0x60000060, new TagInfo(0x60000060, DicomElement.VR.SH, "Compression Code (RET)") );
      tagInfoMap.put( 0x60000100, new TagInfo(0x60000100, DicomElement.VR.US, "Bits Allocated") );
      tagInfoMap.put( 0x60000102, new TagInfo(0x60000102, DicomElement.VR.US, "Bit Position") );
      tagInfoMap.put( 0x60000110, new TagInfo(0x60000110, DicomElement.VR.SH, "Overlay Format (RET)") );
      tagInfoMap.put( 0x60000200, new TagInfo(0x60000200, DicomElement.VR.US, "Overlay Location (RET)") );
      tagInfoMap.put( 0x60001100, new TagInfo(0x60001100, DicomElement.VR.US, "Overlay Descriptor - Gray") );
      tagInfoMap.put( 0x60001101, new TagInfo(0x60001101, DicomElement.VR.US, "Overlay Descriptor - Red") );
      tagInfoMap.put( 0x60001102, new TagInfo(0x60001102, DicomElement.VR.US, "Overlay Descriptor - Green") );
      tagInfoMap.put( 0x60001103, new TagInfo(0x60001103, DicomElement.VR.US, "Overlay Descriptor - Blue") );
      tagInfoMap.put( 0x60001200, new TagInfo(0x60001200, DicomElement.VR.US, "Overlays - Gray") );
      tagInfoMap.put( 0x60001201, new TagInfo(0x60001201, DicomElement.VR.US, "Overlays - Red") );
      tagInfoMap.put( 0x60001202, new TagInfo(0x60001202, DicomElement.VR.US, "Overlays - Green") );
      tagInfoMap.put( 0x60001203, new TagInfo(0x60001203, DicomElement.VR.US, "Overlays - Blue") );
      tagInfoMap.put( 0x60001301, new TagInfo(0x60001301, DicomElement.VR.IS, "ROI Area") );
      tagInfoMap.put( 0x60001302, new TagInfo(0x60001302, DicomElement.VR.DS, "ROI Mean") );
      tagInfoMap.put( 0x60001303, new TagInfo(0x60001303, DicomElement.VR.DS, "ROI Standard Deviation") );
      tagInfoMap.put( 0x60003000, new TagInfo(0x60003000, DicomElement.VR.OW, "Overlay Data") );
      tagInfoMap.put( 0x60004000, new TagInfo(0x60004000, DicomElement.VR.SH, "Group 6000 Comments (RET)") );
      tagInfoMap.put( 0x7FE00000, new TagInfo(0x7FE00000, DicomElement.VR.UL, "Group 7FE0 Length") );
      tagInfoMap.put( 0x7FE00010, new TagInfo(0x7FE00010, DicomElement.VR.OX, "Pixel Data") );
      tagInfoMap.put( 0xFFFEE000, new TagInfo(0xFFFEE000, DicomElement.VR.DL, "Item") );
      tagInfoMap.put( 0xFFFEE00D, new TagInfo(0xFFFEE00D, DicomElement.VR.DL, "Item Delimitation Item") );
      tagInfoMap.put( 0xFFFEE0DD, new TagInfo(0xFFFEE0DD, DicomElement.VR.DL, "Sequence Delimitation Item") );
   }
   
   /**
    * Determines the default value representation for a tag
    * @param tagId DICOM tag
    * @return the appropriate value representation
    */
   public static DicomElement.VR getImplicitVR(int tagId) {
      TagInfo info = tagInfoMap.get(tagId);
      if (info != null) {
         return info.implicitVR;
      }
      return null;
   }
   
   /**
    * Determines the human-readable name of a tag
    * @param tagId DICOM tag identifier
    * @return name of tag
    */
   public static String getName(int tagId) {
      TagInfo info = tagInfoMap.get(tagId);
      if (info != null) {
         return info.name;
      }
      return null;
   }
   
   /**
    * Determines the tag identifier for a DICOM tag with a specified name
    * @param name human-readable name of tag
    * @return DICOM tag identifier, -1 if unknown
    */
   public static int getTagId(String name) {
      for (Entry<Integer,TagInfo> entry : tagInfoMap.entrySet()) {
         if (name.equalsIgnoreCase(entry.getValue().name)) {
            return entry.getValue().tag;
         }
      }
      
      return -1;
   }
   
   /**
    * Adds new tag information for custom tags
    * @param tagId
    * @param name
    * @param vr
    */
   public static void setTagInfo(int tagId, String name, DicomElement.VR vr) {
      tagInfoMap.put(tagId, new TagInfo(tagId, vr, name));
   }
}