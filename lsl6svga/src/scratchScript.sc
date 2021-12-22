;;; Sierra Script 1.0 - (do not remove this comment)
(script# 97)
(include sci.sh)
(use Main)
(use fileScr)
(use Intrface)
(use Sound)
(use Motion)
(use System)

(public
	scratchScript 0
)

(local
	[local0 6] = [913 911 912 910 909 324]
	local6
)
(instance scratchScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (ego edgeHit?)
			(ego normalize: 900 -1 1)
			(theGame handsOn:)
			(self dispose:)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(ego setMotion: 0)
				(= temp0 0)
				(while (<= temp0 5)
					(Load rsVIEW [local0 temp0])
					(++ temp0)
				)
				(cond 
					(global189
						(= global189 0)
						(= global192 (GetNumber {Fidget (1-6):_}))
					)
					((== register 2) (= global192 4))
					((== register 1) (= global192 5) (= local6 1))
					((== curRoomNum 310) (= global192 (Random 5 6)))
					(else (= global192 (Random 1 6)))
				)
				(= register 0)
				(switch global192
					(1
						(if (!= (ego cel?) 4)
							(ego cycleSpeed: 8 setHeading: 125 self)
						else
							(ego heading: 125)
							(= cycles 2)
						)
						(= state 9)
					)
					(2
						(if (!= (ego cel?) 4)
							(ego cycleSpeed: 8 setHeading: 125 self)
						else
							(ego heading: 125)
							(= cycles 2)
						)
					)
					(3
						(= state 19)
						(if (!= (ego cel?) 6)
							(ego cycleSpeed: 8 setHeading: 45 self)
						else
							(ego heading: 45)
							(= cycles 2)
						)
					)
					(else 
						(= state 29)
						(if (!= (ego cel?) 2)
							(ego cycleSpeed: 8 setHeading: 180 self)
						else
							(ego heading: 180)
							(= cycles 2)
						)
					)
				)
			)
			(1 (= cycles 2))
			(2
				(ego
					view: 911
					setLoop: 0 1
					cycleSpeed: 8
					cel: 0
					setCycle: End self
				)
			)
			(3
				(ego setLoop: 1 1 cycleSpeed: 12 setCycle: Fwd)
				(= seconds 5)
			)
			(4
				(ego setLoop: 0 1 cel: 4 setCycle: Beg self)
				(= register 4)
				(= state 36)
			)
			(10 (= cycles 2))
			(11
				(ego
					view: 910
					setLoop: 0 1
					cycleSpeed: 8
					cel: 0
					setCycle: End self
				)
			)
			(12
				(ego view: 910 setLoop: 1 1 cycleSpeed: 12 setCycle: Fwd)
				(sfx number: 43 loop: 1 play: self)
			)
			(13
				(= register 4)
				(= state 36)
				(ego
					view: 911
					setLoop: 0 1
					cycleSpeed: 8
					cel: 4
					setCycle: Beg self
				)
				(sfx number: 0 dispose:)
			)
			(20 (= cycles 2))
			(21
				(ego
					view: 912
					setLoop: 0 1
					cel: 0
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(22
				(ego setLoop: 1 1 setCycle: Fwd)
				(= seconds 3)
			)
			(23
				(if (Random 0 1) (= state (+ state 2)))
				(ego setLoop: 0 1 cel: 3 setCycle: CT 1 -1 self)
			)
			(24
				(messager say: 6 4 0 2 self 620)
				(= ticks 240)
			)
			(25
				(ego view: 900 loop: 8 cel: 6 setCycle: 0)
			)
			(26
				(ego
					normalize: 900 6 1
					cycleSpeed: 8
					setHeading: 180 self
				)
				(= state 36)
			)
			(30 (= cycles 2))
			(31
				(if local6
					(ego
						view: 909
						setLoop: 0 1
						cel: 0
						cycleSpeed: 8
						setCycle: 0
					)
					(messager say: 2 6 0 1 self 0)
				else
					(self cue:)
				)
			)
			(32
				(switch global192
					(4
						(ego
							view: 324
							setLoop: 0 1
							cel: 2
							cycleSpeed: 4
							setCycle: End self
						)
					)
					(5
						(if local6
							(ego setCycle: CT 7 1 self)
						else
							(ego
								view: 909
								setLoop: 0 1
								cel: 0
								cycleSpeed: 8
								setCycle: End self
							)
							(= state 36)
						)
					)
					(else 
						(ego
							view: 909
							setLoop: 1 1
							cel: 0
							cycleSpeed: 8
							setCycle: End self
						)
						(= state 36)
					)
				)
			)
			(33
				(if local6
					(messager say: 0 0 28 1 self 630)
				else
					(= ticks 40)
				)
			)
			(34 (ego setCycle: Beg self))
			(35
				(if local6
					(= state -1)
					(= register 2)
					(= local6 0)
					(messager say: 2 6 0 3 self 0)
				else
					(= ticks 40)
				)
			)
			(36 (ego setCycle: CT 2 1 self))
			(37
				(if
				(and (Btst 55) (== global192 6) (not (Random 0 1)))
					(messager say: 6 4 0 2 self 620)
				else
					(++ state)
					(= ticks 30)
				)
			)
			(38
				(messager say: 0 0 3 2 self 200)
			)
			(39
				(if register
					(ego normalize: 900 register 1)
				else
					(ego normalize: 900 2 1)
				)
				(= temp0 0)
				(while (<= temp0 5)
					(UnLoad 128 [local0 temp0])
					(++ temp0)
				)
				(theGame handsOn:)
				(sfx dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sfx of Sound
	(properties)
)
