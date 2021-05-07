;;; Sierra Script 1.0 - (do not remove this comment)
(script# 406)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	Clock 0
)

(local
	actText = [406 0 406 1 406 2 406 3 406 4 406 5 406 6 406 7]
	chimeDone
	oldMins
	oldSaveDisabled
)
(instance Clock of Script

	(method (doit)
		(if (== state 2)
			(if (== (clockChime prevSignal?) -1)
				(if (== gameMinutes 0)
					(clockChime prevSignal: 0)
					(switch (clockChime number?)
						(94
							(clockChime number: 95 loop: 1 play:)
						)
						(95
							(clockChime number: 96 loop: 1 play:)
						)
						(96
							(clockChime number: 29 loop: gameHours play:)
						)
						(else
							(= chimeDone TRUE)
						)
					)
				else
					(= chimeDone TRUE)
				)
			)
			(if chimeDone
				(clockChime stop: dispose:)
				(= cycles 1)
			)
		)
		(= chimeDone 0)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 406)
		(= saveDisabled oldSaveDisabled)
	)
	
	(method (changeState newState &tmp n [str 25])
		(switch (= state newState)
			(0
				(if (== (User controls?) TRUE)
					(ego setMotion: 0)
				)
				(= oldSaveDisabled saveDisabled)
				(= saveDisabled TRUE)
				(= oldMins gameMinutes)
				(if (> (++ gameMinutes) 3)
					(if (< gameHours 12)
						(++ gameHours)
					else
						(= gameHours 1)
					)
					(= gameMinutes 0)
				)
				(if howFast
					(clockFace
						cel: (- (NumCels clockFace) 1)
						setCycle: BegLoop self
						init:
					)
				else
					(clockFace cel: 0 init: stopUpd:)
					(= cycles 1)
				)
				(hourHand init: hide:)
				(minuteHand init: hide:)
			)
			(1
				(hourHand
					loop: (if (and oldMins (< oldMins 3)) 6 else 5)
					cel: (if (== gameHours 12) 0 else gameHours)
					show:
				)
				(minuteHand loop:
					(switch oldMins
						(0 1)
						(1 3)
						(2 4)
						(3 2)
					)
				)
				(minuteHand
					cel: (if (& oldMins $0001)
						(- (NumCels minuteHand) 1)
					else
						0
					)
					setCycle: (if (& oldMins $0001) BegLoop else EndLoop) self
					show:
				)
			)
			(2
				(if (== gameMinutes 0)
					(if (< gameHours 7) (= n 1) else (= n 5))
					(= n (* (- gameHours n) 2))
					(Print
						(Format @str 406 8 [actText n] [actText (++ n)])
						#at 108 120
						#font 41
						#width 100
						#mode teJustCenter
						#draw
						#dispose
					)
					(clockChime number: 94 loop: 1 play:)
				else
					(clockChime
						number: (+ (- gameMinutes 1) 94)
						loop: 1
						play:
					)
				)
			)
			(3
				(cls)
				(hourHand hide: dispose: delete:)
				(minuteHand hide: dispose: delete:)
				(if howFast
					(clockFace setCycle: EndLoop self)
				else
					(clockFace cel: (clockFace lastCel:) forceUpd:)
					(= cycles 2)
				)
			)
			(4
				(clockFace hide: dispose: delete:)
				(client setScript: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (event claimed?))
				(or
					(== (event type?) mouseDown)
					(== (event type?) keyDown)
					(== (event type?) direction)
				)
			)
			(if (!= (event type?) direction)
				(= chimeDone TRUE)
			)
			(event claimed: TRUE)
		)
	)
)

(instance clockFace of Prop
	(properties
		y 45
		x 159
		view 642
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 1
	)
)

(instance hourHand of Prop
	(properties
		y 45
		x 159
		view 642
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 1
	)
)

(instance minuteHand of Prop
	(properties
		y 45
		x 159
		view 642
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 1
	)
)

(instance clockChime of Sound
	(properties
		priority 15
	)
)
