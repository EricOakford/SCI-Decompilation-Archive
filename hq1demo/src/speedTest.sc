;;; Sierra Script 1.0 - (do not remove this comment)
(script# SPEEDTEST)
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
(instance fred of Actor
	(properties)
)

(instance speedTest of Room
	(properties
		picture 400
		style IRISIN
	)
	
	(method (init)
		(TheMenuBar state: FALSE)
		(HandsOff)
		(super init:)
		(sounds eachElementDo: #stop)
		(while (u> (GetTime) -1024)
		)
		(fred
			view: 799
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
		(if (== (++ machineSpeed) 1) (= doneTime (+ 60 (GetTime))))
		(if
		(and (u< doneTime (GetTime)) (not (self script?)))
			(cond 
				((<= machineSpeed 25) (= howFast 0))
				((<= machineSpeed 40) (= howFast 1))
				((<= machineSpeed 60) (= howFast 2))
				(else (= howFast 3))
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
	(properties)
	
	(method (changeState newState &tmp nextRoom [str 20])
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(theGame setSpeed: 6)
				(= cycles (if debugging 1 else 30))
			)
			(2
				(if debugging
					(repeat
						(= str 0)
						(= nextRoom
							(Print 99 0
								#button {ROLES} ROLES
								#button {DART} DART
								#button {CAVE} CAVE
								#button {FOREST} FOREST
								#button {ARENA} ARENA
								#button {LAST} LAST
								#edit @str 5
								#window SysWindow
							)
						)
						(if str (= nextRoom (ReadNumber @str)))
						(if (> nextRoom 0) (break))
					)
				else
					(= nextRoom INTRO)
				)
				(curRoom newRoom: nextRoom)
			)
		)
	)
)
