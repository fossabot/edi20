package com.inubit.ibis.plugins.edi20.core.parsers.vda.rules;

import org.dom4j.Document;

import com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.commons.rules.AbstractHWFPERule;
import com.inubit.ibis.plugins.edi20.commons.rules.tokens.EDIRuleBaseToken;
import com.inubit.ibis.plugins.edi20.commons.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.commons.rules.tokens.IRuleToken;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 */
public class VDARule extends AbstractHWFPERule {

    private static final String LAST_SEGMENTID = "519";

    /**
     * @param vdaRuleDocument
     *            VDA rule document
     * @throws InubitException
     *             if the given rule document is not a valid VDA rule document
     */
    public VDARule(final Document vdaRuleDocument) throws InubitException {
        super(vdaRuleDocument);
    }

    @Override
    public String getStandard() {
        return "VDA";
    }

    public boolean isEndOfRule() {
        EDIRuleBaseToken segment = getSegment(getCurrentRuleToken());
        if (segment != null) {
            return segment.getID().equals(LAST_SEGMENTID);
        }
        return false;
    }

    private EDIRuleSegment getSegment(final IRuleToken currentRuleToken) {
        if (currentRuleToken instanceof EDIRuleSegment) {
            return (EDIRuleSegment) currentRuleToken;
        }
        return null;
    }

}