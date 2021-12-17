;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15800)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use Scaler)
(use Motion)

(public
	roGuardsHouseLS 0
)

(instance soForestToExt of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 300 235 self)
				((ScriptID 64018 0) setMotion: MoveTo 360 235)
			)
			(1 (curRoom newRoom: 15000))
		)
	)
)

(instance soExtToForest of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 310 340 self)
				((ScriptID 64018 0) setMotion: MoveTo 360 340)
			)
			(1 (curRoom newRoom: 10100))
		)
	)
)

(instance roGuardsHouseLS of TPRoom
	(properties
		picture 15800
	)
	
	(method (init)
		(super init: &rest)
		(ego init: oPanner: setScaler: Scaler 100 64 290 228)
		((ScriptID 64018 0)
			init:
			oPanner:
			bSwing: 0
			setScaler: Scaler 100 64 290 228
		)
		(theGame handsOff:)
		(switch prevRoomNum
			(10100
				(ego posn: 310 340 loop: 3)
				((ScriptID 64018 0) posn: 360 340 loop: 3)
				(ego setScript: soForestToExt)
			)
			(15000
				(ego posn: 300 235 loop: 2)
				((ScriptID 64018 0) posn: 360 235 loop: 2)
				(ego setScript: soExtToForest)
			)
			(else 
				(ego posn: 310 340 loop: 3)
				(ego setScript: soForestToExt)
			)
		)
		(music1 pageSize: 15800)
	)
)
