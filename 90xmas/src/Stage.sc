;;; Sierra Script 1.0 - (do not remove this comment)
(script# STAGE)
(include game.sh)
(use Main)
(use Osc)
(use MoveFwd)
(use LoadMany)
(use Path)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	centerStage 0
)

(local
	cupidPathValue
	cupidTargets
	picDrawn
	local3
	local4
	playingBagPipes
	local6 = [
		0 0
		122 57
		245 61
		163 118
		-1 -1
		]
	cupidPts = [
		225 76
		197 79
		161 81
		129 79
		101 77
		67 71
		PATHEND
		]
	cupidPts2 = [
		101 77
		129 79
		161 81
		197 79
		225 76
		265 71
		PATHEND
		]
	turkeyPoly = [
		0 0
		0 0
		237 80
		1 243
		77 2
		243 69
		3 243
		65 4
		244 65
		5 232
		65 6
		218 67
		7 202
		78 8
		184 96
		9 166
		105 -1
		-1 -1
		]
)
(procedure (NextTake nextPic &tmp thePic)
	((ScriptID MAIN 5) fade:)
	(= thePic (if argc nextPic else pStage))
	(curRoom
		drawPic: thePic
			(if (and (<= 0 numColors) (<= numColors 16))
				BLACKOUT
			else
				FADEOUT
			)
	)
	(cast eachElementDo: #hide)
)

(instance centerStage of Room
	(properties
		picture pStage
		style (| IRISIN DISSOLVE)
	)
	
	(method (init)
		(super init:)
		(Load SOUND sNo)
		(wannaBe init: hide:)
		(stageProp init: hide:)
		(director init: hide:)
		(misHap init: hide:)
		(clover init: setScript: cloverFall)
		(self setScript: bagPipeScript)
	)
)

(instance bagPipeScript of Script
	
	(method (init)
		(super init: &rest)
		((ScriptID MAIN 5) number: sBagPipes loop: -1 playBed:)
		(= playingBagPipes TRUE)
		(self setScript: scottishDance)
	)
	
	(method (doit)
		(super doit:)
		(if (and playingBagPipes (!= (self state?) 0))
			(= playingBagPipes FALSE)
		)
		(if (== (self state?) 0)
			(cond 
				((<= (wannaBe x?) 160) (self cue:))
				(
					(or
						(and
							(== (wannaBe loop?) 0)
							(not (OneOf (wannaBe cel?) 0 3 4 5 8 9 13))
						)
						(and
							(== (wannaBe loop?) 1)
							(not (OneOf (wannaBe cel?) 0 1 2 5 6 9))
						)
					)
					(wannaBe x: (- (wannaBe x?) 2))
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (wannaBe posn: 265 132))
			(1 0)
			(2
				((ScriptID MAIN 4) number: 10 loop: 0 play:)
				(stageProp
					show:
					view: vBagPipe
					setLoop: 2
					cel: 0
					posn: (+ (wannaBe x?) 3) 38
					ignoreActors:
					priority: 10
					signal: 16
					setCycle: EndLoop self
				)
			)
			(3
				((ScriptID 0 4) stop:)
				((ScriptID 0 5) stop:)
				(stageProp stopUpd:)
				(clover setScript: cloverFall 0 1)
			)
			(4
				(clover dispose:)
				(director show: cel: 0 loop: 2 setCycle: EndLoop self)
			)
			(5
				((ScriptID 0 6) number: 101 loop: 0 init: play: self)
			)
			(6
				(Wait 40)
				(Load VIEW vTurkey)
				(LoadMany SOUND sChicken 12 13 14 sWrong)
				(clover setScript: 0)
				(NextTake)
				(= cycles 2)
			)
			(7
				(curRoom setScript: turkeyEnter)
			)
		)
	)
)

(instance cloverFall of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(clover
					setLoop: (Random 3 4)
					y: 30
					setHeading: 180
					setCycle: Forward
				)
				(if (not register)
					(clover
						x: (Random (Random 168 190) (Random 200 230))
						setMotion: MoveFwd (Random (Random 85 99) (Random 99 115)) self
					)
				else
					(clover x: 168 setPri: 11 setMotion: MoveFwd 45 self)
				)
			)
			(1
				(if register
					((ScriptID 0 4) number: 11 loop: 0 play: bagPipeScript)
					(self dispose:)
				else
					(DrawCel vBagPipe
						(clover loop?)
						(clover cel?)
						(clover nsLeft?)
						(clover nsTop?)
						(clover priority?)
					)
					(self changeState: 0)
				)
			)
		)
	)
)

(instance scottishDance of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(or
						(== (client state?) 0)
						playingBagPipes
						(== (wannaBe loop?) 1)
					)
					(-- state)
					(wannaBe
						view: vBagPipe
						setLoop: (if (wannaBe loop?) 0 else 1)
						cel: 0
						setCycle: EndLoop self
						show:
					)
				else
					(wannaBe stopUpd:)
					(client cue:)
					(self dispose:)
				)
			)
		)
	)
)

(instance turkeyEnter of Script

	(method (init)
		(super init: &rest)
		((ScriptID 0 5) number: sChicken loop: -1 playBed:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(stageProp
					view: vTurkey
					setLoop: 1
					cel: 0
					posn: 240 81
					show:
					cycleSpeed: 1
					setPri: 13
					setCycle: CycleTo 4 1
				)
				(wannaBe
					view: vTurkey
					posn: 241 68
					loop: 0
					cel: 0
					setStep: 3 2
					show:
					cycleSpeed: 1
					setCycle: CycleTo 6 1 self
				)
				(= cycles 1)
			)
			(2
				((ScriptID 0 4) number: 12 loop: 0 play:)
			)
			(3 (= cycles 10))
			(4
				(stageProp cycleSpeed: 0 setCycle: BegLoop self)
				(wannaBe cycleSpeed: 0 setCycle: EndLoop self)
			)
			(5 (wannaBe hide:))
			(6
				(stageProp hide:)
				(= picDrawn 0)
				(= cycles 10)
			)
			(7
				(if (!= [turkeyPoly (= picDrawn (+ picDrawn 3))] -1)
					((ScriptID 0 4) number: 13 loop: 0 play:)
					(wannaBe
						show:
						loop: 2
						cel: [turkeyPoly picDrawn]
						posn: [turkeyPoly (+ (-- state) 1)] [turkeyPoly (+ picDrawn 2)]
					)
				)
				(= cycles 1)
			)
			(8
				((ScriptID 0 5) stop:)
				((ScriptID 0 4) number: 14 loop: 0 play:)
				((ScriptID 0 5) fade:)
				(wannaBe
					setLoop: 3
					cel: 0
					posn: 108 136
					setCycle: EndLoop self
					setMotion: MoveTo 103 136
				)
				(= cycles 1)
			)
			(9 (ShakeScreen 3))
			(10
				(director show: cel: 0 loop: 1 setCycle: EndLoop self)
			)
			(11
				((ScriptID 0 6) number: sWrong loop: 0 play: self)
				(LoadMany VIEW vCupid vBodies)
				(Load SCRIPT MOVEFWD)
				(LoadMany SOUND sCupid 16 17 18 sCut)
			)
			(12
				(Wait 40)
				(NextTake)
				(= cycles 2)
			)
			(13
				(curRoom setScript: cupidWannaBe)
				(self dispose:)
			)
		)
	)
)

(instance cupidWannaBe of Script
	
	(method (init)
		(super init: &rest)
		((ScriptID 0 5) number: sCupid loop: -1 playBed:)
		(stageProp posn: 365 132)
	)
	
	(method (doit)
		(super doit:)
		(if (and local4 (== (wannaBe view?) vCupid))
			(stageProp
				show:
				view: vCupid
				setLoop: 3
				cel: 0
				setPri: (- (wannaBe priority?) 1)
				x:
					(if (== (wannaBe loop?) 0)
						(- (wannaBe x?) 1)
					else
						(- (wannaBe x?) 5)
					)
				y: (- (wannaBe y?) 8)
			)
		)
		(if
			(and
				(== cupidPathValue (OneOf cupidPathValue 3 9 15))
				(not (wannaBe cel?))
			)
			(wannaBe setCycle: EndLoop)
		)
		(if (!= cupidTargets local3)
			(= local3 cupidTargets)
			(switch cupidTargets
				(1
					(Bod1 init: startUpd: setCycle: EndLoop Bod1)
				)
				(2
					(Bod2 init: startUpd: setCycle: EndLoop Bod2)
				)
				(3
					(Bod3 init: startUpd: setCycle: EndLoop Bod3)
				)
				(else  (self changeState: 6))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register
					(= cycles 1)
				else
					(= register 1)
					(= seconds 3)
				)
			)
			(1
				(= local4 1)
				((ScriptID 0 4) number: 15 loop: -1 play:)
				(wannaBe
					startUpd:
					view: vCupid
					show:
					setLoop: 1
					cel: 0
					setStep: 10 4
					posn: 255 71
					setMotion: cupidPath self
				)
			)
			(2
				((ScriptID 0 4) stop:)
				(wannaBe stopUpd:)
			)
			(3
				(ShakeScreen 3)
				((ScriptID 0 4) number: 15 loop: -1 play:)
				(wannaBe
					startUpd:
					view: vCupid
					setLoop: 0
					cel: 0
					setMotion: cpdPath2 self
				)
			)
			(4
				((ScriptID 0 4) stop:)
				(wannaBe stopUpd:)
			)
			(5
				(ShakeScreen 3)
				(self changeState: 0)
			)
			(6
				(= local4 0)
				(wannaBe hide:)
				(director show: cel: 0 loop: 0 setCycle: EndLoop self)
			)
			(7
				((ScriptID 0 6) number: sCut loop: 0 play: self)
			)
			(8
				(Wait 40)
				(LoadMany VIEW vGroundhog vBoard)
				(Load SCRIPT JUMP)
				(LoadMany SOUND sGroundhog 19 20 21 14 sNoNoNo)
				(NextTake)
				(= cycles 2)
			)
			(9
				(Bod1 dispose:)
				(Bod2 dispose:)
				(Bod3 dispose:)
				(curRoom setScript: hogScript)
			)
		)
	)
)

(instance hogScript of Script

	(method (init)
		(super init: &rest)
		((ScriptID 0 5) number: sGroundhog loop: -1 playBed:)
		(light init: hide:)
		(stars init: hide:)
		(sparks init: hide:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(Board3 init:)
				(Board4 init:)
				(Board1 init:)
				(Board5 init:)
				(Board2 init:)
				(misHap
					posn: 124 134
					view: vGroundhog
					loop: 3
					cel: 1
					setPri: 8
					show:
				)
				(wannaBe
					view: vGroundhog
					setLoop: 0
					posn: 133 132
					setStep: 3 2
					cycleSpeed: 1
					show:
					setCycle: EndLoop self
				)
				(= cycles 1)
			)
			(2
				((ScriptID 0 4) number: sCrash loop: 0 play:)
			)
			(3
				(stars show: setCycle: Forward)
				(sparks show: setCycle: Forward)
				(= cycles 5)
			)
			(4
				((ScriptID 0 4) number: 20 loop: 0 play:)
				(light show: setPri: (- (wannaBe priority?) 2))
				(stageProp
					view: vGroundhog
					loop: 2
					cel: 0
					posn: 129 86
					setPri: (+ (wannaBe priority?) 1)
					show:
					setCycle: EndLoop self
				)
			)
			(5 (= cycles 8))
			(6
				(stageProp setCycle: BegLoop self)
			)
			(7 (= seconds 3))
			(8
				(stars dispose:)
				(sparks dispose:)
				(stageProp hide:)
				((ScriptID 0 4) number: 21 loop: 0 play:)
				(wannaBe loop: 1 cel: 0 cycleSpeed: 0 setCycle: EndLoop self)
			)
			(9
				(ShakeScreen 3)
				((ScriptID 0 4) number: 14 loop: 0 play:)
				((ScriptID 0 5) fade:)
				(director loop: 5 cel: 0 show: setCycle: EndLoop self)
			)
			(10
				((ScriptID 0 6) number: sNoNoNo loop: 0 play: self)
			)
			(11
				(Wait 40)
				(Load VIEW vSanta vSantaEaster vSantaHop)
				(Load SCRIPT OSC)
				(LoadMany SOUND sBunny 12 23 24 25 27 26 sYesThatsIt sAction)
				(= seconds 4)
			)
			(12
				(NextTake)
				(Board1 dispose:)
				(Board2 dispose:)
				(Board3 dispose:)
				(Board4 dispose:)
				(Board5 dispose:)
				(= cycles 2)
			)
			(13
				(curRoom setScript: santaWannaBe)
				(self dispose:)
			)
		)
	)
)

(instance santaWannaBe of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(stageProp
					view: vSanta
					posn: 238 127
					loop: 0
					cel: 0
					setPri: 13
					cycleSpeed: 1
					show:
					setCycle: EndLoop self
				)
				(wannaBe
					view: vSanta
					posn: 232 122
					loop: 1
					cel: 0
					cycleSpeed: 1
					show:
					setCycle: CycleTo 2 1
				)
				(= cycles 1)
			)
			(2
				((ScriptID 0 4) number: 12 loop: 0 play:)
			)
			(3
				(misHap
					view: vSanta
					loop: 2
					cel: 0
					posn: 224 102
					setPri: 13
					cycleSpeed: 1
					show:
					setCycle: Oscillate 4 self
				)
			)
			(4
				(misHap hide: setPri: -1)
				(wannaBe setCycle: EndLoop)
				(stageProp setCycle: BegLoop self)
			)
			(5
				(stageProp hide: setPri: -1)
				(wannaBe hide:)
				(= seconds 2)
			)
			(6
				(wannaBe
					view: vSanta
					loop: 3
					cel: 0
					posn: 209 131
					cycleSpeed: 1
					show:
					setCycle: CycleTo 3 1 self
				)
			)
			(7
				((ScriptID 0 4) number: 23 loop: 0 play:)
				(wannaBe setCycle: EndLoop self)
			)
			(8
				(wannaBe
					loop: 4
					cel: 0
					posn: 204 133
					setCycle: CycleTo 1 1 self
				)
			)
			(9
				((ScriptID 0 4) number: 24 loop: 0 play:)
				(wannaBe setCycle: CycleTo 2 1 self)
			)
			(10
				(wannaBe cel: 3 posn: 182 138 setCycle: CycleTo 6 1 self)
			)
			(11
				((ScriptID 0 4) number: 23 loop: 0 play:)
				(wannaBe setCycle: CycleTo 7 1 self)
			)
			(12
				(wannaBe posn: 161 137 cel: 8 setCycle: EndLoop self)
			)
			(13
				((ScriptID 0 4) number: 24 loop: 0 play:)
				(misHap
					setLoop: 5
					cel: 0
					setPri: 13
					posn: 169 84
					cycleSpeed: 1
					show:
				)
				(= seconds 2)
			)
			(14
				(Wait 40)
				(misHap hide:)
				(= cycles 5)
			)
			(15
				(director loop: 3 cel: 0 show: setCycle: EndLoop self)
			)
			(16
				((ScriptID 0 6) number: 105 loop: 0 play: self)
			)
			(17 (Wait 40) (= cycles 1))
			(18
				(director loop: 4 cel: 0 setCycle: EndLoop self)
				(misHap
					view: vSantaHop
					loop: 2
					cel: 0
					x: 179
					y: 136
					ignoreHorizon:
					z: 1000
					setPri: 8
				)
			)
			(19
				((ScriptID 0 6) number: 106 loop: 0 play: self)
			)
			(20
				(Wait 60)
				(director hide:)
				(wannaBe
					view: vSantaEaster
					posn: 171 138
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: CycleTo 6 1 self
				)
			)
			(21
				((ScriptID 0 4) number: 25 loop: 0 play:)
				(wannaBe setCycle: EndLoop self)
			)
			(22
				(misHap
					z: 0
					show:
					setCycle: EndLoop
					setMotion: MoveTo 179 133
				)
				(wannaBe
					loop: 1
					cel: 0
					x: 146
					cycleSpeed: 1
					setCycle: CycleTo 7 1 self
				)
			)
			(23
				((ScriptID 0 4) number: 26 loop: 0 play:)
				(wannaBe setCycle: CycleTo 13 1 self)
			)
			(24
				((ScriptID 0 4) stop:)
				(wannaBe setCycle: EndLoop self)
			)
			(25 (= seconds 2))
			(26
				((ScriptID 0 5) number: 4 loop: 0 playBed: self)
				(wannaBe loop: 2 cel: 0 x: 144 setCycle: EndLoop self)
			)
			(27
				(wannaBe
					view: vSantaHop
					setLoop: 0
					cel: 0
					setStep: 4 2
					setCycle: Forward
					cycleSpeed: 0
					setMotion: MoveTo 273 138 self
				)
			)
			(28 (= seconds 2))
			(29
				(wannaBe
					posn: 270 114
					setLoop: 1
					cel: 3
					setStep: 3 2
					setMotion: MoveTo 55 114 self
				)
			)
			(30
				(wannaBe setCycle: Walk dispose:)
			)
			(31
				(director dispose:)
				(arrowSound dispose:)
				((ScriptID 0 5) fade:)
				(Load VIEW vCreditStars)
				(Load SOUND sSugarPlumFairy)
				(= cycles 2)
			)
			(32
				(curRoom setScript: CreditsRoll)
			)
		)
	)
)

(instance CreditsRoll of Script
		
	(method (changeState newState)
		(switch (= state newState)
			(0
				(misHap dispose:)
				(cast eachElementDo: #hide)
				(curRoom drawPic: pCredits2 FADEOUT)
				(LoadMany PICTURE
					pCredits2
					pCredits3
					pCredits4
					pCredits5
					pCredits6
					pCredits7
					pCredits8
					pCredits9
					pCredits10
				)
				(= cycles 1)
			)
			(1
				((ScriptID 0 5) number: sSugarPlumFairy loop: 1 playBed: self)
				(= picDrawn 6)
				(= playingBagPipes 0)
				(= seconds 2)
			)
			(2
				(if (!= (++ picDrawn) 15)
					(-- state)
					(curRoom drawPic: picDrawn 8)
				)
				(= seconds 2)
			)
			(3
				(curRoom drawPic: pCredits1 FADEOUT)
				(= picDrawn 0)
				(= cycles 1)
			)
			(4
				(if (!= [local6 (= picDrawn (+ picDrawn 2))] -1)
					(stageProp
						show:
						view: vCreditStars
						setPri: 15
						posn: [local6 picDrawn] [local6 (+ (-- state) 1)]
						setCycle: Oscillate 1 self
					)
				else
					(stageProp hide:)
				)
			)
			(5
				(curRoom drawPic: pBlack FADEOUT)
				(= cycles 2)
			)
			(6
				(theGame restart:)
			)
		)
	)
)

(class Board of Actor
	(properties
		view vBoard
		loop 0
		cel 0
		signal (| ignrAct fixedLoop)
		illegalBits $0000
		toX 0
		toY 0
	)
	
	(method (init)
		(super init: &rest)
		(self
			setCycle: Forward
			cycleSpeed: 1
			setMotion: JumpTo toX toY self
		)
	)
	
	(method (cue)
		(super cue:)
		(self setCycle: 0 setCel: 50 stopUpd:)
	)
)

(class Bodies of Prop
	(properties
		view vBodies
		loop 0
		cel 0
		signal (| ignrAct fixedLoop stopUpdOn)
	)
	
	(method (init)
		(super init: &rest)
		(arrowSound number: 17 loop: 0 play:)
	)
	
	(method (cue)
		(super cue:)
		(self addToPic:)
		(arrowSound number: 18 loop: 0 play:)
		(cupidWannaBe cue:)
	)
)

(instance wannaBe of Actor
	(properties
		x 300
		y 140
		view vBagPipe
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== (self view?) 311)
				(== (self cel?) 2)
				(== ((self cycler?) cycleCnt?) 0)
			)
			(arrowSound init: number: 16 loop: 0 play:)
			(misHap
				show:
				view: 311
				setLoop: 2
				setCel: (self loop?)
				setStep: 15 2
				x: (if (self loop?)
					(- (self x?) 29)
				else
					(+ (self x?) 21)
				)
				y: (if (self loop?)
					(- (self y?) 1)
				else
					(- (self y?) 1)
				)
				setHeading: (if (self loop?) 260 else 100)
				setMotion: MoveFwd 160
			)
		)
		(if
			(and
				(== (curRoom script?) santaWannaBe)
				(== (self view?) 513)
				(or
					(and (== (self loop?) 0) (== (self cel?) 0))
					(and (== (self loop?) 1) (== (self cel?) 2))
				)
				(== ((self cycler?) cycleCnt?) 0)
			)
			((ScriptID 0 4) number: 27 loop: 0 play:)
		)
	)
)

(instance clover of Actor
	(properties
		y 30
		yStep 3
		view vBagPipe
		signal (| ignrAct ignrHrz)
	)
)

(instance director of Prop
	(properties
		x 72
		y 159
		view vDirector
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance misHap of Actor
	(properties
		x 160
		view vBagPipe
		loop 1
		signal (| ignrAct ignrHrz)
	)
)

(instance Bod1 of Bodies
	(properties
		x 73
		y 140
		loop 1
	)
)

(instance Bod2 of Bodies
	(properties
		x 256
		y 142
	)
)

(instance Bod3 of Bodies
	(properties
		x 75
		y 124
		loop 2
		priority 11
		signal (| ignrAct fixedLoop fixPriOn stopUpdOn) ;$4811
	)
)

(instance stageProp of Prop
	(properties
		x 109
		y 144
		view vGroundhog
		loop 1
		signal (| ignrAct ignrHrz fixedCel fixedLoop) ;$7800
	)
)

(instance light of View
	(properties
		x 163
		y 139
		view vGroundhog
		loop 3
		signal (| ignrAct ignrHrz fixedLoop stopUpdOn) ;$5801
	)
)

(instance Board1 of Board
	(properties
		x 110
		y 126
		loop 1
		toX 98
		toY 116
	)
)

(instance Board2 of Board
	(properties
		x 144
		y 121
		loop 2
		toX 153
		toY 115
	)
)

(instance Board3 of Board
	(properties
		x 137
		y 136
		loop 4
		toX 192
		toY 117
	)
)

(instance Board4 of Board
	(properties
		x 120
		y 124
		priority 12
		signal (| ignrAct fixedLoop fixPriOn)
		toX 105
		toY 141
	)
)

(instance Board5 of Board
	(properties
		x 146
		y 130
		loop 3
		toX 164
		toY 144
	)
)

(instance cupidPath of Path
	
	(method (motionCue)
		(++ cupidTargets)
		(super motionCue: &rest)
	)
	
	(method (at n)
		(return [cupidPts n])
	)
	
	(method (next)
		(super next: &rest)
		(return (++ cupidPathValue))
	)
)

(instance stars of Prop
	(properties
		x 126
		y 92
		view vGroundhog
		loop 4
		priority 14
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance sparks of Prop
	(properties
		x 127
		y 92
		view vGroundhog
		loop 5
		priority 14
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance cpdPath2 of Path

	(method (motionCue)
		(++ cupidTargets)
		(super motionCue: &rest)
	)
	
	(method (at n)
		(return [cupidPts2 n])
	)
	
	(method (next)
		(super next: &rest)
		(return (++ cupidPathValue))
	)
)

(instance arrowSound of Sound
	(properties
		priority 4
	)
)
