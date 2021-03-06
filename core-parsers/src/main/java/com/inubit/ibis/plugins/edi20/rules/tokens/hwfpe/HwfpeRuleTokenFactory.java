package com.inubit.ibis.plugins.edi20.rules.tokens.hwfpe;

import com.inubit.ibis.plugins.edi20.rules.interfaces.IRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleCompositeElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleRoot;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegmentGroup;
import org.dom4j.Element;

import java.util.Hashtable;

/**
 * @author r4fter
 */
public final class HwfpeRuleTokenFactory {

    private static final String NAME_RULESEGMENT = "Segment";
    private static final String NAME_RULEELEMENT = "Element";
    private static final String NAME_RULECOMPOSITEELEMENT = "CompositeElement";
    private static final String NAME_RULESEGMENTGROUP = "SegmentGroup";

    private static final String NAME_MESSAGE = "Message";

    private static final Hashtable<Element, IRuleToken> INSTANCE_CACHE = new Hashtable<>(100);

    public static IRuleToken getInstance(final Element ruleElement) throws IllegalArgumentException {
        if (ruleElement == null) {
            throw new IllegalArgumentException("Element is null!");
        }
        if (INSTANCE_CACHE.containsKey(ruleElement)) {
            final IRuleToken token = INSTANCE_CACHE.get(ruleElement);
            // if (token instanceof EDIRuleBaseToken) {
            //     ((EDIRuleBaseToken) token).resetChildIterator();
            // }
            return token;
        }

        final String ruleElementName = ruleElement.getName();
        if (NAME_MESSAGE.equals(ruleElementName)) {
            return addToCache(ruleElement, new EDIRuleRoot(ruleElement));
        }
        if (NAME_RULESEGMENT.equals(ruleElementName)) {
            return addToCache(ruleElement, new EDIRuleSegment(ruleElement));
        }
        if (NAME_RULEELEMENT.equals(ruleElementName)) {
            return addToCache(ruleElement, new HwfpeRuleElement(ruleElement));
        }
        if (NAME_RULECOMPOSITEELEMENT.equals(ruleElementName)) {
            return addToCache(ruleElement, new EDIRuleCompositeElement(ruleElement));
        }
        if (NAME_RULESEGMENTGROUP.equals(ruleElementName)) {
            return addToCache(ruleElement, new EDIRuleSegmentGroup(ruleElement));
        }
        return null;
    }

    private static IRuleToken addToCache(
            final Element ruleElement,
            final IRuleToken token) {
        if (ruleElement != null && token != null) {
            INSTANCE_CACHE.put(ruleElement, token);
        }
        return token;
    }

}
