;;; Sierra Script 1.0 - (do not remove this comment)
(script# 620)
(include sci.sh)
(use Main)
(use GloryRm)
(use Scaler)

(public
	rm620 0
)

(instance rm620 of GloryRm
	(properties
		picture 620
		style $0400
		north 650
	)
	
	(method (init)
		(switch prevRoomNum
			(650
				(theMusic doSongs: 630 631 632)
				(ego posn: 228 31)
			)
			(640 (ego posn: 54 157))
			(else  (ego posn: 57 154))
		)
		(ego init: setScaler: Scaler 105 32 180 31 normalize:)
		((ScriptID 633 0) init:)
		(super init: 1)
	)
	
	(method (dispose)
		(if script (script dispose:))
		((ScriptID 633 3) stop: clean: (ScriptID 633 3))
		(DisposeScript 633)
		(super dispose:)
	)
)
