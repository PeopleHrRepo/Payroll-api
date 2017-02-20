package com.payroll.constant;

public interface NavigationQueryConstants {
  
  String GET_IMPORTANT_NOTICES =
      "select t1.*,t2.DISPLAY_SEQ as displaySeq from text_tokens t1, splash_content_seq t2 where t1.text_tokenid=t2.text_tokenid order by t2.display_seq";

  String GET_ITEMOF_INTEREST =
      "select t1.*,t2.DISPLAY_SEQ as displaySeq from text_tokens t1, splash_content_seq t2 where t1.text_tokenid=t2.text_tokenid order by t2.display_seq";

  String GET_IMPORTANT_NOTICE_BYID =
      "select t1.*,t2.DISPLAY_SEQ as displaySeq from text_tokens t1, splash_content_seq t2 where t1.text_tokenid=t2.text_tokenid and t1.text_tokenid=:noticeid";

}
