;;; Sierra Script 1.0 - (do not remove this comment)
(script# INTRO) ;200
(include game.sh)
(use Main)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	intro 0
)

(local
	local0 =  2
	preTextTime =  2
	local2 = [2 10]
	cyclesBeforeShake2 =  10
	local5 = [3 3 3 2]
	titleX =  160
	titleY =  120
	targState
	writtenText
	local13
	directedText
	local15
	byText
	programmingText
	robertFischbach
	coreyCole
	larryScott
	jerryShaw
	animationText
	backgroundText
	scenesText
	jeffCrowe
	cindyWalker
	kennNishiuye
	jerryMoore
	musicText
	markSeibert
	gameText
	developmentText
	systemText
	robertHeitman
	jeffStephenson
	pabloGhenis
	stuartGoldstein
	producedText
	gurukaSinghKhalsa1
	gurukaSinghKhalsa2
	gurukaSinghKhalsa3
	executiveText
	producerText
	kenWilliams
)
(procedure (GoToCharacterSelect)
	(SetCursor theCursor TRUE)
	(curRoom newRoom: 202)
)

(instance intro of Room
	(properties
		picture 400
		style DISSOLVE
	)
	
	(method (init)
		(LoadMany PICTURE 460 400 906)
		(LoadMany VIEW
			vHalfDragon vHalfFlame vSierraPresents vQFGshadow vQFG
			vTitleSpell vEgoRunning vEgoFleeing vSaurus vDragon vDragonFight
		)
		(super init:)
		(HandsOff)
		(cond 
			((== numVoices 1)
				(Load SOUND 201)
				(introMusic number: 201)
			)
			((<= numVoices 4)
				(Load SOUND 301)
				(introMusic number: 301)
			)
			(else
				(Load SOUND 1)
				(introMusic number: 1)
			)
		)
		(keyDownHandler add: self)
		(mouseDownHandler add: self)
		(self setScript: page1Script)
	)
	
	(method (handleEvent event)
		(cond 
			((== (event type?) mouseDown)
				(event claimed: TRUE)
				(cSound stop:)
				(GoToCharacterSelect)
			)
			((super handleEvent: event))
			((== (event type?) keyDown)
				(switch (event message?)
					(ENTER
						(event claimed: TRUE)
						(cSound stop:)
						(GoToCharacterSelect)
					)
					(`#2
						(ToggleSound)
					)
					(`^q
						(PromptQuit)
					)
				)
			)
			(else
				(event claimed: TRUE)
			)
		)
	)
)

(instance page1Script of Script
	(method (doit)
		(switch (introMusic prevSignal?)
			(20 (= targState 4))
			(30 (= targState 6))
			(40 (= targState 9))
			(50 (= targState 13))
		)
		(if (and (> targState state) (or seconds cycles))
			(= seconds (= cycles 0))
			(self cue:)
		else
			(super doit:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= targState 0) (= cycles 2))
			(1
				(SetCursor theCursor FALSE)
				(introMusic init: play:)
				(= cycles 1)
			)
			(2
				(curRoom drawPic: 906)
				(if (== howFast slow)
					(self cue:)
				else
					(= seconds preTextTime)
				)
			)
			(3
				(sierraText cel: 0 init: stopUpd:)
				(presentsText cel: 0 init: stopUpd:)
				(= seconds 8)
			)
			(4
				(claw1 cel: 0 init: cycleSpeed: 2 setCycle: EndLoop self)
			)
			(5
				(ShakeScreen 3)
				(= seconds 5)
			)
			(6
				(claw1 stopUpd:)
				(claw2 cel: 0 init: cycleSpeed: 2 setCycle: EndLoop)
				(= cycles cyclesBeforeShake2)
			)
			(7
				(claw2 stopUpd:)
				(ShakeScreen 3)
				(head
					setLoop: 2
					cel: 0
					setPri: 6
					posn: 83 68
					init:
					cycleSpeed: (if (== howFast slow) 0 else 2)
					setCycle: EndLoop self
				)
			)
			(8
				(head
					setLoop: 3
					cel: 0
					posn: 87 49
					init:
					setCycle: CycleTo 4 1
				)
				(= seconds 5)
			)
			(9 (head setCycle: CycleTo 5 1 self))
			(10
				(flame
					posn: 85 76
					setLoop: 0
					setCel: -1
					cel: 0
					setPri: 8
					ignoreActors:
					illegalBits: 0
					init:
					xStep: 3
					yStep: 6
					setMotion: MoveTo 218 228
					setCycle: EndLoop self
				)
			)
			(11
				(sierraText cycleSpeed: 2 setCycle: CycleTo 2 1 self)
				(flame
					setLoop: 1
					cel: 0
					cycleSpeed: (if (== howFast slow) 0 else 1)
					setMotion: MoveTo 158 228
					setCycle: Forward
				)
				(head setCycle: EndLoop)
			)
			(12
				(sierraText setCycle: EndLoop)
				(presentsText cycleSpeed: 2 setCycle: EndLoop)
				(= seconds (+ howFast fastest))
			)
			(13
				(cast eachElementDo: #dispose eachElementDo: #delete)
				(client setScript: page2Script)
			)
		)
	)
)

(instance page2Script of Script
	(method (doit)
		(if (== -1 (introMusic prevSignal?)) (= targState 15))
		(if (and (> targState state) (or seconds cycles))
			(= seconds (= cycles 0))
			(self cue:)
		else
			(super doit:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= targState 0)
				(curRoom drawPic: 400)
				(switch howFast
					(slow
						(questText posn: 117 70 cycleSpeed: 0 cel: 2)
						(forText posn: 162 70 cycleSpeed: 0 cel: 2)
						(gloryText posn: 208 70 cycleSpeed: 0 cel: 2)
					)
					(medium
						(questText posn: 133 115 cycleSpeed: 1 cel: 0)
						(forText posn: 161 115 cycleSpeed: 1 cel: 0)
						(gloryText posn: 189 115 cycleSpeed: 1 cel: 0)
					)
					(fast
						(questText posn: 150 160 cycleSpeed: 2 cel: 0)
						(forText posn: 160 160 cycleSpeed: 2 cel: 0)
						(gloryText posn: 170 160 cycleSpeed: 2 cel: 0)
					)
					(else 
						(questText posn: 160 200 cycleSpeed: 2 cel: 0)
						(forText posn: 160 200 cycleSpeed: 2 cel: 0)
						(gloryText posn: 160 200 cycleSpeed: 2 cel: 0)
						(if (> machineSpeed 80)
							(questText moveSpeed: 1)
							(forText moveSpeed: 1)
							(gloryText moveSpeed: 1)
						)
					)
				)
				(questText
					setLoop: 0
					setPri: 1
					ignoreActors:
					illegalBits: 0
					xStep: 16
					yStep: 10
					init:
					setCycle: EndLoop
					setMotion: MoveTo 76 37 self
				)
				(forText
					setLoop: 1
					setPri: 1
					ignoreActors:
					illegalBits: 0
					xStep: 16
					yStep: 10
					init:
					setCycle: EndLoop
					setMotion: MoveTo 150 59 self
				)
				(gloryText
					setLoop: 2
					setPri: 1
					ignoreActors:
					illegalBits: 0
					xStep: 16
					yStep: 10
					init:
					setCycle: EndLoop
					setMotion: MoveTo 243 99 self
				)
			)
			(1)
			(2)
			(3
				(questText view: vQFG setLoop: 0 cel: 0 setCycle: Forward)
				(forText view: vQFG setLoop: 1 cel: 0 setCycle: Forward)
				(gloryText view: vQFG setLoop: 2 cel: 0 setCycle: Forward)
				(= seconds (if (== howFast slow) 1 else 5))
			)
			(4
				(questText stopUpd:)
				(forText stopUpd:)
				(gloryText stopUpd:)
				(cond 
					((== howFast slow)
						(saurus posn: 200 137 cycleSpeed: 0 moveSpeed: 0)
						(aHero posn: 120 137 cycleSpeed: 0 moveSpeed: 0)
					)
					((== howFast medium)
						(saurus posn: 90 137 cycleSpeed: 1 moveSpeed: 1)
						(aHero posn: 0 137 cycleSpeed: 1 moveSpeed: 1)
					)
					((== howFast fast)
						(saurus posn: 0 137 cycleSpeed: 1 moveSpeed: 1)
						(aHero posn: -100 137 cycleSpeed: 1 moveSpeed: 1)
					)
					(else
						(saurus posn: -50 137 cycleSpeed: 1 moveSpeed: 1)
						(aHero posn: -170 137 cycleSpeed: 1 moveSpeed: 1)
					)
				)
				(saurus
					setLoop: 0
					ignoreActors:
					init:
					setCycle: Forward
					setMotion: MoveTo 400 137
				)
				(aHero
					setLoop: 0
					ignoreActors:
					init:
					setCycle: Forward
					setMotion: MoveTo 340 137 self
				)
			)
			(5
				(saurus hide: stopUpd:)
				(aHero hide: stopUpd:)
				(if (>= howFast fast)
					(questText startUpd:)
					(forText startUpd:)
					(gloryText startUpd:)
				)
				(if (!= howFast slow)
					(titleSpell
						setLoop: 0
						cel: -1
						setPri: 8
						ignoreActors:
						illegalBits: 0
						xStep: 8
						yStep: 8
						posn: titleX titleY
						init:
						setCycle: EndLoop self
					)
					(if (== howFast fastest)
						(titleSpell cycleSpeed: 2 moveSpeed: 2)
					)
				else
					(self cue:)
				)
			)
			(6
				(if (!= howFast slow)
					(titleSpell2
						setLoop: 0
						cel: (titleSpell cel?)
						setPri: 8
						ignoreActors:
						illegalBits: 0
						xStep: 8
						yStep: 8
						posn: titleX titleY
						init:
						setCycle: Forward
						setMotion: MoveTo 231 160
					)
					(titleSpell setCycle: Forward setMotion: MoveTo 89 160 self)
					(if (== howFast fastest)
						(titleSpell2 cycleSpeed: 2 moveSpeed: 2)
					)
				else
					(self cue:)
				)
			)
			(7
				(soYou init: stopUpd:)
				(want init: stopUpd:)
				(toBeA init: stopUpd:)
				(heroWord init: stopUpd:)
				(if (!= howFast slow)
					(titleSpell setCycle: EndLoop)
					(titleSpell2 setCycle: EndLoop self)
				else
					(self cue:)
				)
			)
			(8
				(if (!= howFast slow)
					(titleSpell dispose:)
					(titleSpell2 dispose:)
				)
				(questText stopUpd:)
				(forText stopUpd:)
				(gloryText stopUpd:)
				(saurus
					view: vDragon
					setLoop: 1
					cel: 0
					xStep: 8
					yStep: 5
					cycleSpeed: 0
					moveSpeed: 0
					show:
				)
				(aHero
					view: vEgoFleeing
					setLoop: 0
					cel: 0
					cycleSpeed: 0
					moveSpeed: 0
					show:
				)
				(switch howFast
					(slow
						(aHero posn: 330 137 setMotion: MoveTo 230 137 self)
						(saurus posn: 390 137 setMotion: MoveTo 250 137)
					)
					(fastest
						(aHero posn: 400 137 setMotion: MoveTo 100 137 self)
						(saurus posn: 500 137 setMotion: MoveTo 120 137)
					)
					(else 
						(aHero posn: 350 137 setMotion: MoveTo 140 137 self)
						(saurus posn: 420 137 setMotion: MoveTo 160 137)
					)
				)
			)
			(9
				(cast eachElementDo: #dispose eachElementDo: #delete)
				(curRoom drawPic: 460)
				(quest2Text setPri: 1 ignoreActors: init: stopUpd:)
				(for2Text setPri: 1 ignoreActors: init: stopUpd:)
				(glory2Text setPri: 1 ignoreActors: init: stopUpd:)
				(dragonTail cycleSpeed: 4 init:)
				(if (== howFast slow)
					(dragonTail stopUpd:)
				else
					(dragonTail setScript: drTailScript)
				)
				(dragonHead
					setPri: 10
					init:
					cycleSpeed: 1
					setCycle: CycleTo 5 1 self
				)
			)
			(10
				(if (== howFast slow)
					(self changeState: 14)
				)
				(= cycles 8)
			)
			(11
				(dragonHead cycleSpeed: 2 setCycle: CycleTo 1 1 self)
			)
			(12
				(= cycles 8)
			)
			(13
				(dragonHead setCycle: CycleTo 5 1 self)
			)
			(14
				(dragonHead stopUpd:)
				(dragonTail setCycle: 0 setScript: 0 stopUpd:)
				(quest2Text setCycle: Forward)
				(for2Text setCycle: Forward)
				(glory2Text setCycle: Forward)
				(= seconds 15)
			)
			(15
				(dragonHead stopUpd:)
				(dragonTail setCycle: 0 setScript: 0 stopUpd:)
				(if (== howFast slow)
					(= cycles 1)
				else
					(= seconds 2)
				)
			)
			(16
				(cast eachElementDo: #dispose eachElementDo: #delete)
				(client setScript: creditScript)
			)
		)
	)
)

(instance creditScript of Script
	(method (doit)
		(switch (cSound prevSignal?)
			(10 (= targState 3))
			(20 (= targState 6))
			(30 (= targState 9))
			(40 (= targState 12))
			(50 (= targState 15))
			(60 (= targState 18))
			(70 (= targState 21))
		)
		(if (and (> targState state) (or seconds cycles))
			(= seconds (= cycles 0))
			(self cue:)
		else
			(super doit:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= targState 0)
				(Load PICTURE 903)
				(Load VIEW vCreditsDragon)
				(Load VIEW vCredits)
				(Load SOUND (SoundFX 61))
				(Load SOUND (SoundFX 73))
				(curRoom drawPic: 903 HSHUTTER)
				(leftDrag
					view: vCreditsDragon
					loop: 0
					cel: 0
					posn: 285 168
					priority: 12
					init:
					stopUpd:
				)
				(rightDrag
					view: vCreditsDragon
					loop: 1
					cel: 0
					posn: 32 168
					priority: 12
					init:
					stopUpd:
				)
				(andAnd view: vCredits loop: 3 cel: 0 posn: 155 23 init: hide:)
				(byBy view: vCredits loop: 0 cel: 1 posn: 155 40 init: hide:)
				(= cycles 1)
			)
			(1
				((= writtenText (View new:))
					view: vCredits
					loop: 0
					cel: 0
					posn: 110 23
					init:
					stopUpd:
				)
				(andAnd show:)
				((= directedText (View new:))
					view: vCredits
					loop: 0
					cel: 3
					posn: 203 23
					init:
					stopUpd:
				)
				(byBy show:)
				((= byText (View new:))
					view: vCredits
					loop: 1
					cel: 0
					posn: 157 90
					init:
					stopUpd:
				)
				(= seconds 4)
			)
			(2
				(cSound number: (SoundFX 61) loop: -1 play:)
				(= seconds 5)
			)
			(3
				(byText dispose:)
				(writtenText dispose:)
				(directedText dispose:)
				(byBy hide:)
				(andAnd dispose:)
				(= seconds 1)
			)
			(4
				((= programmingText (View new:))
					view: vCredits
					loop: 0
					cel: 4
					posn: 157 28
					init:
					stopUpd:
				)
				(if (== howFast slow) (= cycles 1) else (= seconds 1))
			)
			(5
				((= robertFischbach (View new:))
					view: vCredits
					loop: 1
					cel: 1
					posn: 158 80
					init:
					stopUpd:
				)
				((= coreyCole (View new:))
					view: vCredits
					loop: 1
					cel: 2
					posn: 158 107
					init:
					stopUpd:
				)
				((= larryScott (View new:))
					view: vCredits
					loop: 3
					cel: 2
					posn: 158 134
					init:
					stopUpd:
				)
				((= jerryShaw (View new:))
					view: vCredits
					loop: 3
					cel: 3
					posn: 158 161
					init:
					stopUpd:
				)
				(= seconds 11)
			)
			(6
				(programmingText dispose:)
				(robertFischbach dispose:)
				(coreyCole dispose:)
				(larryScott dispose:)
				(jerryShaw dispose:)
				(= seconds 1)
			)
			(7
				((= animationText (View new:))
					view: vCredits
					loop: 0
					cel: 5
					posn: 143 18
					init:
					stopUpd:
				)
				(byBy loop: 3 cel: 0 posn: 201 18 show:)
				((= backgroundText (View new:))
					view: vCredits
					loop: 0
					cel: 6
					posn: 124 36
					init:
					stopUpd:
				)
				((= scenesText (View new:))
					view: vCredits
					loop: 0
					cel: 7
					posn: 211 36
					init:
					stopUpd:
				)
				(if (== howFast slow) (= cycles 1) else (= seconds 1))
			)
			(8
				((= kennNishiuye (View new:))
					view: vCredits
					loop: 1
					cel: 3
					posn: 158 80
					init:
					stopUpd:
				)
				((= jerryMoore (View new:))
					view: vCredits
					loop: 3
					cel: 1
					posn: 158 107
					init:
					stopUpd:
				)
				((= jeffCrowe (View new:))
					view: vCredits
					loop: 1
					cel: 4
					posn: 158 134
					init:
					stopUpd:
				)
				((= cindyWalker (View new:))
					view: vCredits
					loop: 5
					cel: 2
					posn: 158 161
					init:
					stopUpd:
				)
				(= seconds 9)
			)
			(9
				(animationText dispose:)
				(backgroundText dispose:)
				(scenesText dispose:)
				(byBy hide:)
				(kennNishiuye dispose:)
				(jeffCrowe dispose:)
				(cindyWalker dispose:)
				(jerryMoore dispose:)
				(= seconds 1)
			)
			(10
				((= musicText (View new:))
					view: vCredits
					loop: 5
					cel: 0
					posn: 156 21
					init:
					stopUpd:
				)
				(byBy loop: 0 cel: 1 posn: 156 36 show:)
				(if (== howFast slow)
					(= cycles 1)
				else
					(= seconds 1)
				)
			)
			(11
				((= markSeibert (View new:))
					view: vCredits
					loop: 5
					cel: 1
					posn: 159 98
					init:
					stopUpd:
				)
				(= seconds 7)
			)
			(12
				(musicText dispose:)
				(byBy hide:)
				(markSeibert dispose:)
				(= seconds 1)
			)
			(13
				((= gameText (View new:))
					view: vCredits
					loop: 0
					cel: 8
					posn: 99 22
					init:
					stopUpd:
				)
				((= developmentText (View new:))
					view: vCredits
					loop: 0
					cel: 9
					posn: 180 22
					init:
					stopUpd:
				)
				((= systemText (View new:))
					view: vCredits
					loop: 0
					cel: 10
					posn: 156 37
					init:
					stopUpd:
				)
				(if (== howFast slow)
					(= cycles 1)
				else
					(= seconds 1)
				)
			)
			(14
				((= jeffStephenson (View new:))
					view: vCredits
					loop: 2
					cel: 0
					posn: 157 76
					init:
					stopUpd:
				)
				((= robertHeitman (View new:))
					view: vCredits
					loop: 2
					cel: 1
					posn: 157 98
					init:
					stopUpd:
				)
				((= pabloGhenis (View new:))
					view: vCredits
					loop: 2
					cel: 2
					posn: 158 122
					init:
					stopUpd:
				)
				((= stuartGoldstein (View new:))
					view: vCredits
					loop: 2
					cel: 3
					posn: 158 146
					init:
					stopUpd:
				)
				(= seconds 9)
			)
			(15
				(gameText dispose:)
				(developmentText dispose:)
				(systemText dispose:)
				(jeffStephenson dispose:)
				(robertHeitman dispose:)
				(pabloGhenis dispose:)
				(stuartGoldstein dispose:)
				(= seconds 1)
			)
			(16
				((= producedText (View new:))
					view: vCredits
					loop: 0
					cel: 11
					posn: 157 23
					init:
					stopUpd:
				)
				(byBy loop: 0 cel: 1 posn: 156 40 show:)
				(if (== howFast slow)
					(= cycles 1)
				else
					(= seconds 1)
				)
			)
			(17
				((= gurukaSinghKhalsa1 (View new:))
					view: vCredits
					loop: 4
					cel: 0
					posn: 106 81
					init:
					stopUpd:
				)
				((= gurukaSinghKhalsa2 (View new:))
					view: vCredits
					loop: 4
					cel: 1
					posn: 159 98
					init:
					stopUpd:
				)
				((= gurukaSinghKhalsa3 (View new:))
					view: vCredits
					loop: 4
					cel: 2
					posn: 211 116
					init:
					stopUpd:
				)
				(= seconds 9)
			)
			(18
				(producedText dispose:)
				(byBy dispose:)
				(gurukaSinghKhalsa1 dispose:)
				(gurukaSinghKhalsa2 dispose:)
				(gurukaSinghKhalsa3 dispose:)
				(= seconds 1)
			)
			(19
				((= executiveText (View new:))
					view: vCredits
					loop: 0
					cel: 12
					posn: 158 21
					init:
					stopUpd:
				)
				((= producerText (View new:))
					view: vCredits
					loop: 0
					cel: 13
					posn: 159 36
					init:
					stopUpd:
				)
				(if (== howFast slow)
					(= cycles 1)
				else
					(= seconds 1)
				)
			)
			(20
				((= kenWilliams (View new:))
					view: vCredits
					loop: 4
					cel: 3
					posn: 159 98
					init:
					stopUpd:
				)
				(= seconds 10)
			)
			(21
				(GoToCharacterSelect)
			)
		)
	)
)

(instance claw1 of Prop
	(properties
		y 92
		x 51
		view vHalfDragon
		priority 3
	)
)

(instance claw2 of Prop
	(properties
		y 87
		x 127
		view vHalfDragon
		loop 1
		cel 1
		priority 3
	)
)

(instance head of Prop
	(properties
		y 49
		x 87
		view vHalfDragon
		loop 2
	)
)

(instance flame of Actor
	(properties
		view vHalfFlame
	)
)

(instance sierraText of Prop
	(properties
		y 168
		x 128
		view vSierraPresents
		priority 12
	)
)

(instance presentsText of Prop
	(properties
		y 182
		x 130
		view vSierraPresents
		loop 1
		priority 12
	)
)

(instance questText of Actor
	(properties
		y 160
		x 150
		view vQFGshadow
	)
)

(instance quest2Text of Actor
	(properties
		y 37
		x 76
		view vQFG
		illegalBits $0000
	)
)

(instance forText of Actor
	(properties
		y 160
		x 150
		view vQFGshadow
		loop 1
	)
)

(instance for2Text of Actor
	(properties
		y 59
		x 150
		view vQFG
		loop 1
		illegalBits $0000
	)
)

(instance gloryText of Actor
	(properties
		y 160
		x 150
		view vQFGshadow
		loop 2
		priority 12
	)
)

(instance glory2Text of Actor
	(properties
		y 99
		x 243
		view vQFG
		loop 2
		illegalBits $0000
	)
)

(instance titleSpell of Actor
	(properties
		view vTitleSpell
	)
)

(instance titleSpell2 of Actor
	(properties
		view vTitleSpell
	)
)

(instance soYou of View
	(properties
		y 160
		x 50
		view vTitleSpell
		loop 1
		priority 7
	)
)

(instance want of View
	(properties
		y 160
		x 127
		view vTitleSpell
		loop 1
		cel 1
		priority 7
	)
)

(instance toBeA of View
	(properties
		y 160
		x 209
		view vTitleSpell
		loop 1
		cel 2
		priority 7
	)
)

(instance heroWord of View
	(properties
		y 160
		x 286
		view vTitleSpell
		loop 1
		cel 3
		priority 7
	)
)

(instance introMusic of Sound
	(properties
		number 1
	)
)

(instance saurus of Actor
	(properties
		y 137
		x -20
		yStep 4
		view vSaurus
		xStep 6
	)
)

(instance aHero of Actor
	(properties
		y 137
		x -80
		yStep 4
		view vEgoRunning
		xStep 7
	)
)

(instance dragonHead of Prop
	(properties
		y 118
		x 170
		view vDragonFight
	)
)

(instance dragonTail of Prop
	(properties
		y 121
		x 87
		view vDragonFight
		loop 1
		priority 8
	)
)

(instance drTailScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: EndLoop self))
			(1
				(= state -1)
				(client setCycle: BegLoop self)
			)
		)
	)
)

(instance leftDrag of View)

(instance rightDrag of View)

(instance andAnd of View)

(instance byBy of View)
