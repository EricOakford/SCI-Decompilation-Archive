;;; Sierra Script 1.0 - (do not remove this comment)
(script# 560)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use DPath)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm560 0
)

(local
	local0
	originalScore
	local2
	obstacleX
	local4
	oldEgoX
	[local6 2]
	obstacleLoop
	[msgBuf 40]
	[titleBuf 22]
)
(instance rm560 of Room
	(properties
		picture 560
		horizon -10
	)
	
	(method (init)
		(Load SOUND 4)
		(Load SOUND 561)
		(Load SCRIPT DPATH)
		(= obstacleX 222)
		(= originalScore score)
		(super init:)
		(addToPics add: atpHorizon doit:)
		(aDot init:)
		(aRightBank init:)
		(aLeftBank init:)
		(aObstacle init:)
		(self setScript: RoomScript)
		(NormalEgo)
		(ego
			view: 561
			posn: 222 113
			setPri: 6
			setStep: 3 1
			setCycle: Forward
			init:
		)
		(HandsOff)
		(User canControl: TRUE)
		(music number: 560 loop: -1 priority: 9999 play:)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (and (== state 2) (< (aObstacle distanceTo: ego) 9))
			(self changeState: 6)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aObstacle hide:)
				(if (== (DotScript state?) 5)
					(self changeState: 4)
				else
					(= cycles (Random 5 10))
				)
			)
			(1
				(if (not (Random 0 2))
					(= obstacleX (Random 200 235))
					(aLeftBank setMotion: MoveTo obstacleX 92)
					(aRightBank setMotion: MoveTo obstacleX 92 self)
				else
					(self cue:)
				)
				(if (and (not (Random 0 2)) (< DotScript 3))
					(= state -1)
				)
			)
			(2
				(if (> (= oldEgoX (Random 200 400)) 240)
					(= oldEgoX (ego x?))
				)
				(cond 
					((< oldEgoX (- obstacleX 5))
						(- oldEgoX 5)
					)
					((> oldEgoX (+ obstacleX 5))
						(+ oldEgoX 5)
					)
				)
				(aObstacle
					posn: obstacleX 89
					setLoop: (Random 0 obstacleLoop)
					cel: obstacleLoop
					setCycle: EndLoop
					show:
					setMotion: MoveTo oldEgoX (+ (ego y?) 5) self
				)
				(if (== (aObstacle loop?) 3)
					(-- obstacleLoop)
					(Print 560 1 #at -1 10)
				)
			)
			(3
				(if (== (aObstacle loop?) 3)
					(Print 560 2 #at -1 10)
				)
				(theGame changeScore: obstacleLoop)
				(self changeState: 0)
			)
			(4
				(Print 560 3 #at -1 10)
				(theGame changeScore: (- 150 (- score originalScore)))
				(theGame changeScore: (- 150 (- score originalScore)))
				(= seconds 3)
			)
			(5
				(Print 560 4 #at -1 10)
				(DisposeScript DPATH)
				(music priority: 0)
				(curRoom newRoom: 580)
			)
			(6
				(= seconds (= cycles 0))
				(HandsOff)
				(aLeftBank setCel: 0)
				(aRightBank setCel: 0)
				(aDot setScript: 0 setMotion: 0 setCel: 0)
				(aObstacle hide:)
				(ego setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(7
				(music stop:)
				(Print 560 5 #at -1 10)
				(soundFX number: 4 loop: 1 play:)
				(aLog init:)
				(ego
					cycleSpeed: 0
					illegalBits: 0
					ignoreActors:
					setStep: 3 3
					setLoop: 5
					cel: 0
					setCycle: EndLoop self
					setMotion: MoveTo (aLeftBank x?) 100 self
				)
			)
			(8)
			(9
				(soundFX number: 561 loop: 1 play:)
				(theGame setScript: (ScriptID DYING))
				((ScriptID DYING)
					caller: 563
					register: (Format @msgBuf 560 6)
					next: (Format @titleBuf 560 7)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) keyDown)
				(== (event claimed?) FALSE)
				(== (event message?) `#8)
				(< state 4)
			)
			(Print 560 0)
			(= score originalScore)
			(Bset fSkippedRafting)
			(curRoom newRoom: 580)
			(return)
		)
	)
)

(instance DotScript of Script
	(method (changeState newState)
		(ChangeScriptState self newState 2 2)
		(switch (= state newState)
			(0
				(= seconds 1)
			)
			(1
				(= obstacleLoop 0)
				(aObstacle setStep: 2 2 cycleSpeed: 1)
				(aDot
					setMotion: DPath
						209 179 192 183 176 186 151 187
						127 186 116 182 108 182 101 179
						self
				)
			)
			(2
				(= obstacleLoop 1)
				(aObstacle setStep: 3 3 cycleSpeed: 0)
				(aDot
					setMotion: DPath
						93 178 78 173 66 164 55 150 51 140
						41 133 36 126 36 115 35 109 29 104
						31 96 35 85 38 78
						self
				)
			)
			(3
				(= obstacleLoop 3)
				(aObstacle setStep: 4 4)
				(aDot
					setMotion: DPath 35 73 39 65 45 59 48 52 56 44 67 34 78 22 self
				)
			)
			(4
				(aDot setMotion: DPath 86 15 100 9 109 4 112 -3 self)
			)
		)
	)
)

(instance aDot of Actor
	(properties
		y 188
		x 258
		view 560
		cycleSpeed 1
		moveSpeed 1
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			setScript: DotScript
			setCycle: Forward
			setLoop: 3
			setStep: 1 1
			setPri: 15
			illegalBits: 0
			ignoreActors:
		)
	)
)

(instance atpHorizon of PicView
	(properties
		y 91
		x 223
		view 560
		loop 1
		signal ignrAct
	)
)

(instance aLeftBank of Actor
	(properties
		y 92
		x 222
		view 560
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 0
			setCycle: Forward
			setStep: 2 2
			illegalBits: 0
			ignoreActors:
		)
	)
)

(instance aRightBank of Actor
	(properties
		y 92
		x 222
		view 560
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 2
			setCycle: Forward
			setStep: 2 2
			illegalBits: 0
			ignoreActors:
		)
	)
)

(instance aObstacle of Actor
	(properties
		y 87
		x 222
		view 562
	)
	
	(method (init)
		(super init:)
		(self setPri: 5 illegalBits: 0 ignoreActors:)
	)
)

(instance aLog of View
	(properties
		view 561
		loop 6
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setPri: 5 posn: (ego x?) (ego y?))
	)
)
