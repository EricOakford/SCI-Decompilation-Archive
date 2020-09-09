;;; Sierra Script 1.0 - (do not remove this comment)
(script# DART)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Sound)
(use Save)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	dagNabIt 0
)

(local
	forceMeter
	angleMeter
	theHand
	theDagger
	egoDagger
	egoLabel
	chiefLabel
	daggerX
	daggerY
	hitX
	hitY
	local11
	daggersThrown
	egoPoints
	chiefPoints
	local15
	local16 =  2
	local17 =  1
	egosTurn
	local19
	local20 =  10
	forceCel =  5
	angleCel =  10
	[dags 12]
	dagIndex
	saveSpeed
	[str 50]
)
(procedure (ShowScores strg theColor theX theY)
	(Display
		(Format @str 5 0 strg)
		p_at theX theY
		p_mode teJustLeft
		p_font 999
		p_color theColor
		p_back vBLACK
		p_width 18
	)
)

(procedure (SetHandCoords)
	(= daggerX (Random 40 100))
	(= daggerY (Random 85 100))
)

(procedure (DisposeDaggers)
	(while (>= (-- dagIndex) 0)
		([dags dagIndex] dispose:)
	)
	(= dagIndex 0)
)

(procedure (localproc_0069 &tmp temp0 temp1)
	(= temp0 7)
	(= hitX
		(+ 70 (- (= temp1 (Random 0 temp0)) (/ temp0 2)))
	)
	(= temp0 (- temp0 temp1))
	(= hitY (+ 45 (- (Random 0 temp0) (/ temp0 2))))
)

(procedure (localproc_009d &tmp temp0 temp1)
	(= temp0 26)
	(= hitX
		(+ 70 (- (= temp1 (Random 0 temp0)) (/ temp0 2)))
	)
	(= temp0 (- temp0 temp1))
	(= hitY (+ 45 (- (Random 0 temp0) (/ temp0 2))))
)

(instance dagNabIt of Room
	(properties
		picture rDagNabIt
		style IRISIN
	)
	
	(method (init)
		(LoadMany VIEW rDagNabIt vDartBoard)
		(LoadMany SOUND
			sKnifeStick1
			sKnifeStick2
			sKnifeThrow
			sKnifeStick1IBM
			sKnifeStick2IBM
			sKnifeThrowIBM
		)
		(super init: &rest)
		(if (== numVoices 1)
			(dagSound number: sKnifeThrowIBM)
			(heroDag number: sKnifeStick1IBM)
			(chiefDag number: sKnifeStick2IBM)
		else
			(dagSound number: sKnifeThrow)
			(heroDag number: sKnifeStick1)
			(chiefDag number: sKnifeStick2)
		)
		((View new:)
			view: vDartBoard
			loop: 0
			cel: 0
			posn: 70 45
			ignoreActors:
			setPri: 0
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vDartBoard
			loop: 1
			cel: 0
			posn: 68 183
			ignoreActors:
			setPri: 13
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vDartBoard
			loop: 2
			cel: 0
			posn: 183 180
			ignoreActors:
			setPri: 1
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vDartBoard
			loop: 2
			cel: 1
			posn: 272 180
			ignoreActors:
			setPri: 1
			init:
			stopUpd:
			addToPic:
		)
		((= egoLabel (View new:))
			view: vDartBoard
			loop: 5
			cel: 0
			ignoreActors:
			posn: 27 178
			setPri: 14
			init:
		)
		((= chiefLabel (View new:))
			view: vDartBoard
			loop: 5
			cel: 1
			ignoreActors:
			posn: 110 178
			setPri: 14
			init:
		)
		(dagSound init:)
		(heroDag init:)
		(chiefDag init:)
		(self setScript: dagnabitScript)
	)
)

(instance throwScript of Script
	
	(method (changeState newState &tmp theCel addPoints)
		(switch (= state newState)
			(0)
			(1
				(= saveSpeed speed)
				(theGame setSpeed: 0)
				(theHand cycleSpeed: 2 setCycle: CycleTo 2 1 self)
				(if egosTurn
					(egoDagger cycleSpeed: 2 setCycle: CycleTo 2 1)
				)
			)
			(2
				(theHand cycleSpeed: 0 setCycle: EndLoop self)
				(if egosTurn
					(egoDagger cycleSpeed: 0 setCycle: EndLoop)
				)
				(dagSound play:)
			)
			(3
				(theHand stopUpd:)
				(if egosTurn (egoDagger posn: 900 200 stopUpd:))
				(theDagger
					posn: daggerX daggerY
					setPri: 4
					setLoop: (if egosTurn 4 else 0)
					cel: 0
					cycleSpeed: 0
					moveSpeed: 0
					ignoreActors:
					ignoreControl: -1
					setCycle: EndLoop self
					setMotion: MoveTo hitX (+ hitY 1)
				)
			)
			(4
				(theDagger
					setPri: 3
					setLoop: (if egosTurn 5 else 1)
					cel: 0
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Forward
					setMotion: MoveTo hitX hitY self
				)
			)
			(5
				(theDagger posn: 900 100 stopUpd:)
				(theHand cel: 0 forceUpd: stopUpd:)
				((= [dags dagIndex] (View new:))
					view: 340
					loop: (if egosTurn 7 else 3)
					cel: 3
					posn: hitX hitY
					ignoreActors:
					setPri: 1
					init:
					stopUpd:
				)
				(++ dagIndex)
				(dagSound stop:)
				(if egosTurn (heroDag play:) else (chiefDag play:))
				(= theCel
					(cond 
						((< hitX 65) 0)
						((> hitX 75) 2)
						(else 1)
					)
				)
				(= hitX (+ (* (- hitX 70) 2) 229))
				(= hitY (+ (* (- hitY 45) 2) 61))
				((= [dags dagIndex] (View new:))
					view: 340
					loop: (if egosTurn 7 else 3)
					cel: theCel
					posn: hitX hitY
					ignoreActors:
					setPri: 1
					init:
					stopUpd:
				)
				(++ dagIndex)
				(= addPoints
					(switch (OnControl CMAP hitX hitY)
						(cBLUE 1)
						(cGREEN 2)
						(cCYAN 3)
						(cRED 4)
						(else  0)
					)
				)
				(if egosTurn
					(= egoPoints (+ egoPoints addPoints))
				else
					(= chiefPoints (+ chiefPoints addPoints))
				)
				(theGame setSpeed: saveSpeed)
				(client cue:)
			)
		)
	)
)

(instance dagnabitScript of Script

	(method (init)
		((= forceMeter (View new:))
			view: vDartBoard
			loop: 3
			cel: forceCel
			posn: 160 178
			ignoreActors:
			setPri: 2
			init:
			stopUpd:
		)
		((= angleMeter (View new:))
			view: vDartBoard
			loop: 4
			cel: angleCel
			posn: 272 178
			ignoreActors:
			setPri: 2
			init:
			stopUpd:
		)
		(SetHandCoords)
		((= theHand (Prop new:))
			view: 340
			setLoop: 2
			cel: 0
			posn: 900 0
			setPri: 6
			ignoreActors:
			init:
			stopUpd:
		)
		((= theDagger (Actor new:))
			view: 340
			setLoop: 0
			cel: 0
			posn: 900 100
			setPri: 4
			ignoreActors:
			ignoreHorizon:
			init:
			stopUpd:
		)
		((= egoDagger (Actor new:))
			view: 340
			setLoop: 6
			cel: 0
			posn: 900 200
			setPri: 7
			ignoreActors:
			init:
			stopUpd:
		)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 5 1
					#mode teJustCenter
					#dispose
					#window aWin
				)
				(= seconds 8)
			)
			(1
				(cls)
				(egoLabel hide:)
				(++ local17)
				(= local16
					(= local15 (= local11 (= egoPoints (= chiefPoints 0))))
				)
				(= local19 60)
				(= dagIndex 0)
				(ShowScores egoPoints vYELLOW 47 173)
				(ShowScores chiefPoints vLCYAN 73 173)
				(self cue:)
			)
			(2
				(= daggersThrown 0)
				(= egosTurn TRUE)
				(SetHandCoords)
				(self cue:)
			)
			(3
				(DisposeDaggers)
				(self setScript: throwScript)
				(self cue:)
			)
			(4
				(theHand cel: 0 posn: daggerX daggerY forceUpd:)
				(egoDagger
					setLoop: 6
					cel: 0
					posn: daggerX daggerY
					setPri: 7
					forceUpd: self
				)
				(self cue:)
			)
			(5 (= cycles 20))
			(6
				(localproc_009d)
				(++ local16)
				(script changeState: 1)
			)
			(7
				(ShowScores egoPoints vYELLOW 47 173)
				(if (< (++ daggersThrown) 3)
					(SetHandCoords)
					(self changeState: 4)
				else
					(theHand posn: 900 0)
					(= cycles 20)
				)
			)
			(8
				(egoLabel show:)
				(chiefLabel hide:)
				(= egosTurn 0)
				(= daggersThrown 0)
				(SetHandCoords)
				(self cue:)
			)
			(9
				(self setScript: throwScript)
				(self cue:)
			)
			(10
				(theHand cel: 0 posn: daggerX daggerY forceUpd:)
				(= cycles 20)
			)
			(11
				(localproc_0069)
				(++ local15)
				(script changeState: 1)
			)
			(12
				(ShowScores chiefPoints 11 73 173)
				(if (< (++ daggersThrown) 3)
					(self changeState: 10)
				else
					(chiefLabel show:)
					(theHand posn: 900 0)
					(++ local11)
					(= cycles 5)
				)
			)
			(13
				(if (< local11 1)
					(self changeState: 2)
				else
					(self cue:)
				)
			)
			(14
				(DisposeDaggers)
				(curRoom newRoom: GHOSTS)
			)
		)
	)
)

(instance dagSound of Sound
	(properties
		priority 2
	)
)

(instance heroDag of Sound
	(properties
		priority 2
	)
)

(instance chiefDag of Sound
	(properties
		priority 2
	)
)

(instance aWin of SysWindow
	(properties
		color vRED
	)
)
