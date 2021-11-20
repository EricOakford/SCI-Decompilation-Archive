;;; Sierra Script 1.0 - (do not remove this comment)
(script# 621)
(include sci.sh)
(use Main)
(use GloryRm)
(use Scaler)

(public
	rm621 0
)

(instance rm621 of GloryRm
	(properties
		modNum 620
		picture 620
		north 640
	)
	
	(method (init)
		(switch prevRoomNum
			(640 (ego posn: 88 32))
			(660 (ego posn: 263 152))
			(else  (ego posn: 237 152))
		)
		(ego init: setScaler: Scaler 105 32 180 31 normalize:)
		((ScriptID 633 0) init:)
		(super init: &rest)
		(theGame handsOn:)
	)
	
	(method (dispose)
		(if script (script dispose:))
		((ScriptID 633 3) stop: clean: (ScriptID 633 3))
		(DisposeScript 633)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 0 1 0 0 0 621)
		else
			(super doVerb: theVerb)
		)
	)
)
