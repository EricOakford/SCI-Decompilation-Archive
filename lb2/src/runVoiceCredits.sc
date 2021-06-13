;;; Sierra Script 1.0 - (do not remove this comment)
(script# 781)
(include game.sh)
(use Main)
(use Motion)
(use Actor)
(use System)

(public
	runVoiceCredits 0
)

(local
	local0 =  20
	i
	local2
	[local3 16]
	[local19 16]
	local35
)
(procedure (localproc_0014)
	(= local2 0)
	(= i 0)
	(while (< i (creditTitle lastCel:))
		(= [local3 i]
			(creditName
				x: 15
				view:
				(switch local0
					(20 798)
					(21 799)
					(22 794)
				)
				new:
			)
		)
		([local3 i] setLoop: 0 setCel: (+ i 1))
		(++ local2)
		(++ i)
	)
)

(procedure (localproc_007c)
	(= local2 0)
	(= i 0)
	(while (< i (creditTitle lastCel:))
		(= [local19 i]
			(creditName
				x: 210
				view:
				(switch local0
					(20 798)
					(21 799)
					(22 794)
				)
				new:
			)
		)
		([local19 i] setLoop: 1 setCel: (+ i 1))
		(++ local2)
		(++ i)
	)
)

(instance runVoiceCredits of Script

	(method (changeState newState &tmp [temp0 100])
		(switch (= state newState)
			(0
				(creditTitle
					view:
					(switch local0
						(20 798)
						(21 799)
						(22 794)
					)
					setLoop: 0
					init:
					addToPic:
				)
				(localproc_0014)
				(localproc_007c)
				(= cycles 1)
			)
			(1
				(= local35 40)
				(= i 0)
				(= cycles 1)
			)
			(2
				([local3 i] init:)
				([local19 i] init:)
				([local3 i] setMotion: MoveTo 15 local35)
				([local19 i] setMotion: MoveTo 210 local35 self)
				(= cycles 1)
			)
			(3
				(if (>= (+ i 1) local2) (= state 4) else 0)
			)
			(4
				([local3 i] addToPic:)
				([local19 i] addToPic:)
				(++ i)
				(= local35 (+ (/ 164 local2) local35))
				(= state 1)
				(self cue:)
			)
			(5 (= ticks 300))
			(6
				(addToPics eachElementDo: #dispose)
				(addToPics eachElementDo: #delete)
				(cast eachElementDo: #dispose)
				(cast eachElementDo: #delete)
				(curRoom drawPic: 780)
				(= cycles 2)
			)
			(7 (= cycles 2))
			(8
				(if (< local0 22)
					(++ local0)
					(self changeState: -1)
					(= cycles 2)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance creditTitle of Actor
	(properties
		x 9
		y 20
	)
)

(instance creditName of Actor
	(properties
		x 86
		y 220
		cel 1
		signal ignrAct
		moveSpeed 1
	)
)
