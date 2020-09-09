;;; Sierra Script 1.0 - (do not remove this comment)
(script# SPEED)
(include game.sh)
(use Main)
(use Intrface)
(use Save)
(use Motion)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	speedTest 0
)

(local
	doneTime
)
(instance fred of Actor)

(instance speedTest of Room
	(properties
		picture pBlack
		style IRISIN
	)
	
	(method (init)
		(HandsOff)
		(super init:)
		(sounds eachElementDo: #stop)
		(fred
			view: vSpeed
			setLoop: 0
			illegalBits: 0
			posn: 20 99
			setStep: 1 1
			setMotion: MoveTo 300 100
			setCycle: Forward
			init:
		)
		(theGame setSpeed: 0)
		(= machineSpeed 0)
	)
	
	(method (doit)
		(super doit:)
		(if (== (++ machineSpeed) 1)
			(= doneTime (+ 60 (GetTime)))
		)
		(if
		(and (u< doneTime (GetTime)) (not (self script?)))
			(cond 
				((<= machineSpeed 25)
					(= howFast slow)
				)
				((<= machineSpeed 40)
					(= howFast medium)
				)
				((<= machineSpeed 60)
					(= howFast fast)
				)
				(else
					(= howFast fastest)
				)
			)
			(self setScript: speedScript)
		)
	)
	
	(method (dispose)
		(User blocks: 0)
		(super dispose:)
	)
)

(instance speedScript of Script
	
	(method (changeState newState &tmp nextRoom [str 20])
		(switch (= state newState)
			(0
				(= cycles 1)
			)
			(1
				(theGame setSpeed: 6)
				(= cycles (if debugging 1 else 30))
			)
			(2
				(if debugging
					(repeat
						(= str 0)
						(= nextRoom
							(Print SPEED 0
								#button {INTRO} INTRO
								#button {INN} INN
								#button {ALLEY} ALLEY
								#button {SCORP} SCORP
								#button {ROPE} ROPE
								#button {IBLIS} IBLIS
								#edit @str 5
								#window SysWindow
							)
						)
						(if str
							(= nextRoom (ReadNumber @str))
						)
						(if (> nextRoom 0)
							(break)
						)
					)
				else
					(= nextRoom INTRO)
				)
				(TheMenuBar state: TRUE)
				(curRoom newRoom: nextRoom)
			)
		)
	)
)
