;;; Sierra Script 1.0 - (do not remove this comment)
(script# 627)
(include sci.sh)
(use Main)
(use GloryRm)
(use Scaler)

(public
	rm627 0
)

(local
	local0
)
(instance rm627 of GloryRm
	(properties
		modNum 620
		picture 620
		north 630
	)
	
	(method (init &tmp temp0)
		(= local0 (Btst 112))
		(switch prevRoomNum
			(630 (ego posn: 88 32))
			(662 (ego posn: 276 152))
			(else  (ego posn: 237 152))
		)
		(ego init: setScaler: Scaler 105 32 180 31 normalize:)
		((ScriptID 633 0) init:)
		(super init: &rest)
		(if (and Night (not (Btst 112))) (theGame save: 1))
	)
	
	(method (dispose)
		((ScriptID 633 3) stop: clean: (ScriptID 633 3))
		(if script (script dispose:))
		(DisposeScript 633)
		(if local0 (Bset 112) else (Bclr 112))
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 0 1 0 0 0 627)
		else
			(super doVerb: theVerb)
		)
	)
)
