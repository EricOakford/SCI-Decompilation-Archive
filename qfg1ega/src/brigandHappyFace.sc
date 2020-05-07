;;; Sierra Script 1.0 - (do not remove this comment)
(script# 227)
(include game.sh)
(use Main)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	happyFace 0
)

(instance ball of Actor
	(properties
		y 78
		x 267
		view vJesterRoom
		loop 7
		cel 5
	)
)

(instance happyFace of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 227)
		(DisposeScript JUMP)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 247 119 self)
			)
			(1
				(ego view: vEgoJesterRoom setLoop: 6 setCel: 0)
				(HighPrint 227 0)
				;It's a little sign that reads: "Do not read this sign under any circumstances.
				(= cycles 3)
			)
			(2
				((ScriptID 96 11) setCycle: EndLoop self)
			)
			(3
				((ScriptID 96 11) hide:)
				(ball
					illegalBits: 0
					ignoreActors:
					setPri: 8
					setLoop: 7
					setCel: 5
					init:
				)
				(= cycles 2)
			)
			(4
				((ScriptID 96 16)
					number: (SoundFX 86)
					loop: 1
					priority: 2
					play:
				)
				(ball posn: 261 85)
				(ego setCel: 1)
				(= cycles 2)
			)
			(5
				((ScriptID 96 16) stop:)
				(ego
					moveSpeed: 2
					setMotion: MoveTo 249 123
					cycleSpeed: 2
					setCycle: CycleTo 3 1 self
				)
				(ball setMotion: JumpTo 195 189)
				(if (not (Btst PULLED_CHAIN))
					((ScriptID 96 6) setCel: 3)
					((ScriptID 96 5)
						setLoop: 4
						cel: 0
						cycleSpeed: 1
						setCycle: Forward
					)
				)
			)
			(6
				((ScriptID 96 11) show: cycleSpeed: 0 setCycle: BegLoop)
				(ego setCycle: EndLoop self)
			)
			(7
				(Bset FLAG_258)
				(ball dispose:)
				(= cycles 2)
			)
			(8
				((ScriptID 96 11) stopUpd:)
				(Bset FLAG_269)
				(client setScript: 0)
			)
		)
	)
)
