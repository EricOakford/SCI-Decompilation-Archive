;;; Sierra Script 1.0 - (do not remove this comment)
(script# 741)
(include sci.sh)
(use Main)
(use EgoDead)
(use Scaler)
(use PolyPath)
(use Jump)
(use Motion)
(use System)

(public
	sLeaveThisRoom 0
	enterScript 1
	sFallsBoom 2
)

(local
	local0
)
(instance sLeaveThisRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: 0)
				(if (Btst 340)
					(ego solvePuzzle: 456 15)
					(= seconds 2)
				else
					(messager say: 11 4 24 0 self)
				)
			)
			(1
				(if (Btst 340)
					(curRoom newRoom: 720)
				else
					(ego setMotion: PolyPath (ego x?) (+ (ego y?) 10) self)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (not state)
			(if (< local0 100)
				(Palette palSET_FLAG 66 85 (= local0 (+ local0 10)))
				(FrameOut)
			else
				(self cue:)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 75 (+ (ego y?) 10))
			)
			(1
				(ego normalize: 2)
				(if (Btst 340)
					(curRoom setScript: (ScriptID 740 2))
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
		)
	)
)

(instance sFallsBoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 740 1) dispose:)
				(ego
					view: 30
					setLoop: 3 1
					setCel: 0
					setSpeed: 6
					setCycle: CT 5 1
					setScaler: Scaler 40 37 175 47
					setMotion: JumpTo 187 125 self
				)
			)
			(1 (EgoDead 5 740))
		)
	)
)
