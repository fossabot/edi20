package com.inubit.ibis.plugins.edi20.parsers.vda.scanners;

import com.inubit.ibis.plugins.edi20.parsers.vda.delimiters.VDADelimiters;
import com.inubit.ibis.plugins.edi20.scanners.EDILexicalScanner;
import com.inubit.ibis.plugins.edi20.scanners.IToken;

/**
 * @author r4fter
 */
public class VDALexicalScanner extends EDILexicalScanner {

    /**
     * @param textInputDocument
     *         text input document
     * @param delimiters
     *         VDA delimiters
     */
    public VDALexicalScanner(final StringBuilder textInputDocument, final VDADelimiters delimiters) {
        super(textInputDocument, delimiters);
    }

    private VDADelimiters getVDADelimiter() {
        return (VDADelimiters) getDelimiters();
    }

    @Override
    protected IToken getNextToken(final int position) {
        int index = getIndexOfNextDelimiter(position);
        if (index == -1) {
            // no next token found
            index = getInputDocument().length();
        }
        String str = getInputDocument().substring(position, index);
        return VDATokenFactory.getInstance(getVDADelimiter()).getToken(str, position);
    }

    private int getIndexOfNextDelimiter(final int position) {
        return getIndexOfDelimiter(position, getVDADelimiter().getSegmentDelimiter());
    }

}
