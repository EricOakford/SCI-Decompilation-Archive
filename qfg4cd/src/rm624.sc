;;; Sierra Script 1.0 - (do not remove this comment)
(script# 624)
(include sci.sh)
(use Main)
(use GloryRm)
(use Scaler)

(public
	rm624 0
)

(instance rm624 of GloryRm
	(properties
		picture 620
		style $0400
		north 642
	)
	
	(method (init)
		(switch prevRoomNum
			(642 (ego posn: 228 31))
			(643 (ego posn: 54 157))
			(else  (ego posn: 228 31))
		)
		(ego
			init:
			setScaler: Scaler 105 32 180 31
			normalize: (if (== prevRoomNum 643) 2 else 3)
		)
		((ScriptID 633 0) init:)
		(super init: &rest)
	)
	
	(method (dispose)
		(if script (script dispose:))
		((ScriptID 633 3) stop: clean: (ScriptID 633 3))
		(DisposeScript 633)
		(super dispose:)
	)
)
