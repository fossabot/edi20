package com.inubit.ibis.plugins.edi20.parsers;

import java.util.ArrayList;
import java.util.List;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 */
public final class EDIParserRegistry {

    private static final List<IEDIParser> REGISTERED_PARSER = new ArrayList<IEDIParser>();

    public static void register(IEDIParser parser) {
        REGISTERED_PARSER.add(parser);
    }

    public static void deregister(IEDIParser parser) {
        REGISTERED_PARSER.remove(parser);
    }

    /**
     * @param textInputDocument
     *         text input document
     * @return EDI parser factory instance
     */
    public static EDIParserRegistry getInstance(final StringBuilder textInputDocument) {
        return new EDIParserRegistry(textInputDocument);
    }

    private StringBuilder fTextInputDocument;

    private EDIParserRegistry(final StringBuilder textInputDocument) {
        fTextInputDocument = textInputDocument;
    }

    /**
     * @return parser instance
     *
     * @throws InubitException
     *         if retrieving parser instance failed
     */
    public IEDIParser getParser() throws InubitException {
        for (IEDIParser parser : REGISTERED_PARSER) {
            if (parser.canParse(fTextInputDocument)) {
                return parser;
            }
        }
        throw new InubitException("Unknown format of input document!");
    }

    /**
     * @param rule
     *         EDI rule
     * @return parser instance for the given rule
     *
     * @throws InubitException
     *         if no parser was found for the given rule
     */
    public IEDIParser getParser(final AbstractEDIRule rule) throws InubitException {
        for (IEDIParser parser : REGISTERED_PARSER) {
            if (parser.canParse(rule)) {
                return parser;
            }
        }
        throw new InubitException("Unknown format of input document!");
    }

}