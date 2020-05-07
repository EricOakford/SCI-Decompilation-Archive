;;; Sierra Script 1.0 - (do not remove this comment)
(script# INTRO)
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
	[local2 2] = [2 10]
	cyclesBeforeShake2 =  10
	[local5 4] = [3 3 3 2]
	titleSpellX =  160
	titleSpellY =  120
	local11
	local12
)
(instance intro of Room
	(properties
		picture 400
		style IRISIN
	)
	
	(method (init)
		(LoadMany PICTURE 460 400 906)
		(LoadMany VIEW 906 907 908 918 919 913 5 7 430 460 462)
		(super init:)
		(cond 
			((== numVoices 1) (Load SOUND 201) (introMusic number: 201))
			((<= numVoices 4) (Load SOUND 301) (introMusic number: 301))
			(else (Load SOUND 1) (introMusic number: 1))
		)
		(TheMenuBar state: TRUE)
		(self setScript: page1Script)
	)
)

(instance page1Script of Script
	(properties)
	
	(method (doit)
		(switch (introMusic prevSignal?)
			(20 (= local11 4))
			(30 (= local11 6))
			(40 (= local11 9))
			(50 (= local11 13))
		)
		(if (and (> local11 state) (or seconds cycles))
			(= seconds (= cycles 0))
			(self cue:)
		else
			(super doit:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= local11 0) (= cycles 2))
			(1
				(introMusic init: play:)
				(= cycles 1)
			)
			(2
				(curRoom drawPic: 906)
				(= seconds preTextTime)
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
					posn: 143 68
					init:
					cycleSpeed: (if (== howFast 0) 0 else 2)
					setCycle: EndLoop self
				)
			)
			(8
				(head
					setLoop: 3
					cel: 0
					posn: 147 49
					init:
					setCycle: CycleTo 4 1
				)
				(= seconds 5)
			)
			(9 (head setCycle: CycleTo 5 1 self))
			(10
				(flame
					posn: 145 76
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
					cycleSpeed: (if (== howFast 0) 0 else 1)
					setMotion: MoveTo 218 228
					setCycle: Forward
				)
				(head setCycle: EndLoop)
			)
			(12
				(sierraText setCycle: EndLoop)
				(presentsText cycleSpeed: 2 setCycle: EndLoop)
				(= seconds (+ howFast 3))
			)
			(13
				(cast eachElementDo: #dispose eachElementDo: #delete)
				(client setScript: page2Script)
			)
		)
	)
)

(instance page2Script of Script
	(properties)
	
	(method (doit)
		(if (== (introMusic prevSignal?) -1) (= local11 14))
		(if (and (> local11 state) (or seconds cycles))
			(= seconds (= cycles 0))
			(self cue:)
		else
			(super doit:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local11 0)
				(curRoom drawPic: 400)
				(switch howFast
					(0
						(heroText posn: 117 70 cycleSpeed: 0 cel: 2)
						(questText posn: 208 70 cycleSpeed: 0 cel: 2)
					)
					(1
						(heroText posn: 133 115 cycleSpeed: 1 cel: 0)
						(questText posn: 189 115 cycleSpeed: 1 cel: 0)
					)
					(2
						(heroText posn: 150 160 cycleSpeed: 2 cel: 0)
						(questText posn: 170 160 cycleSpeed: 2 cel: 0)
					)
					(else 
						(heroText posn: 160 200 cycleSpeed: 2 cel: 0)
						(questText posn: 160 200 cycleSpeed: 2 cel: 0)
						(if (> howFast 80)
							(heroText moveSpeed: 1)
							(questText moveSpeed: 1)
						)
					)
				)
				(heroText
					setLoop: 0
					setPri: 12
					ignoreActors:
					illegalBits: 0
					xStep: 16
					yStep: 10
					init:
					setCycle: EndLoop
					setMotion: MoveTo 85 50 self
				)
				(questText
					setLoop: 1
					setPri: 12
					ignoreActors:
					illegalBits: 0
					xStep: 16
					yStep: 10
					init:
					setCycle: EndLoop
					setMotion: MoveTo 244 50 self
				)
			)
			(1)
			(2
				(heroText view: 919 setLoop: 0 cel: 0 setCycle: Forward)
				(questText view: 919 setLoop: 1 cel: 0 setCycle: Forward)
				(= seconds (if (== howFast 0) 1 else 5))
			)
			(3
				(heroText stopUpd:)
				(questText stopUpd:)
				(cond 
					((== howFast 0)
						(saurus posn: 200 125 cycleSpeed: 0 moveSpeed: 0)
						(aHero posn: 120 125 cycleSpeed: 0 moveSpeed: 0)
					)
					((== howFast 1)
						(saurus posn: 90 125 cycleSpeed: 1 moveSpeed: 1)
						(aHero posn: 0 125 cycleSpeed: 1 moveSpeed: 1)
					)
					((== howFast 2)
						(saurus posn: 0 125 cycleSpeed: 1 moveSpeed: 1)
						(aHero posn: -100 125 cycleSpeed: 1 moveSpeed: 1)
					)
					(else
						(saurus posn: -50 125 cycleSpeed: 1 moveSpeed: 1)
						(aHero posn: -170 125 cycleSpeed: 1 moveSpeed: 1)
					)
				)
				(saurus
					setLoop: 0
					ignoreActors:
					init:
					setCycle: Forward
					setMotion: MoveTo 400 125
				)
				(aHero
					setLoop: 0
					ignoreActors:
					init:
					setCycle: Forward
					setMotion: MoveTo 340 125 self
				)
			)
			(4
				(saurus hide: stopUpd:)
				(aHero hide: stopUpd:)
				(if (>= howFast 2)
					(heroText startUpd:)
					(questText startUpd:)
				)
				(if (!= howFast 0)
					(titleSpell
						setLoop: 0
						cel: -1
						setPri: 8
						ignoreActors:
						illegalBits: 0
						xStep: 8
						yStep: 8
						posn: titleSpellX titleSpellY
						init:
						setCycle: EndLoop self
					)
					(if (== howFast 3)
						(titleSpell cycleSpeed: 2 moveSpeed: 2)
					)
				else
					(self cue:)
				)
			)
			(5
				(if (!= howFast 0)
					(titleSpell2
						setLoop: 0
						cel: (titleSpell cel?)
						setPri: 8
						ignoreActors:
						illegalBits: 0
						xStep: 8
						yStep: 8
						posn: titleSpellX titleSpellY
						init:
						setCycle: Forward
						setMotion: MoveTo 231 160
					)
					(titleSpell setCycle: Forward setMotion: MoveTo 89 160 self)
					(if (== howFast 3)
						(titleSpell2 cycleSpeed: 2 moveSpeed: 2)
					)
				else
					(self cue:)
				)
			)
			(6
				(soYou init: stopUpd:)
				(want init: stopUpd:)
				(toBeA init: stopUpd:)
				(heroWord init: stopUpd:)
				(if (!= howFast 0)
					(titleSpell setCycle: EndLoop)
					(titleSpell2 setCycle: EndLoop self)
				else
					(self cue:)
				)
			)
			(7
				(if (!= howFast 0)
					(titleSpell dispose:)
					(titleSpell2 dispose:)
				)
				(heroText stopUpd:)
				(questText stopUpd:)
				(saurus
					view: 460
					setLoop: 1
					cel: 0
					xStep: 8
					yStep: 5
					cycleSpeed: 0
					moveSpeed: 0
					show:
				)
				(aHero
					view: 7
					setLoop: 0
					cel: 0
					cycleSpeed: 0
					moveSpeed: 0
					show:
				)
				(switch howFast
					(0
						(aHero posn: 330 125 setMotion: MoveTo 230 125 self)
						(saurus posn: 390 125 setMotion: MoveTo 250 125)
					)
					(3
						(aHero posn: 400 125 setMotion: MoveTo 100 125 self)
						(saurus posn: 500 125 setMotion: MoveTo 120 125)
					)
					(else 
						(aHero posn: 350 125 setMotion: MoveTo 140 125 self)
						(saurus posn: 420 125 setMotion: MoveTo 160 125)
					)
				)
			)
			(8
				(cast eachElementDo: #dispose eachElementDo: #delete)
				(curRoom drawPic: 460)
				(hero2Text setPri: 12 ignoreActors: init: stopUpd:)
				(quest2Text setPri: 12 ignoreActors: init: stopUpd:)
				(dragonTail cycleSpeed: 4 init:)
				(if (== howFast 0)
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
			(9
				(if (== howFast 0) (self changeState: 13))
				(= cycles 8)
			)
			(10
				(dragonHead cycleSpeed: 2 setCycle: CycleTo 1 1 self)
			)
			(11 (= cycles 8))
			(12
				(dragonHead setCycle: CycleTo 5 1 self)
			)
			(13
				(dragonHead stopUpd:)
				(dragonTail setCycle: 0 setScript: 0 stopUpd:)
				(hero2Text setCycle: Forward)
				(quest2Text setCycle: Forward)
				(= seconds 15)
			)
			(14
				(dragonHead stopUpd:)
				(dragonTail setCycle: 0 setScript: 0 stopUpd:)
				(if (== howFast 0) (= cycles 1) else (= seconds 2))
			)
			(15 (curRoom newRoom: ROLES))
		)
	)
)

(instance claw1 of Prop
	(properties
		y 92
		x 111
		view 906
		priority 3
	)
)

(instance claw2 of Prop
	(properties
		y 87
		x 187
		view 906
		loop 1
		cel 1
		priority 3
	)
)

(instance head of Prop
	(properties
		y 49
		x 147
		view 906
		loop 2
	)
)

(instance flame of Actor
	(properties
		view 907
	)
)

(instance sierraText of Prop
	(properties
		y 168
		x 188
		view 908
		priority 12
	)
)

(instance presentsText of Prop
	(properties
		y 182
		x 190
		view 908
		loop 1
		priority 12
	)
)

(instance heroText of Actor
	(properties
		y 160
		x 150
		view 918
	)
)

(instance hero2Text of Actor
	(properties
		y 50
		x 85
		view 919
		illegalBits $0000
	)
)

(instance questText of Actor
	(properties
		y 160
		x 150
		view 918
		loop 1
		priority 12
	)
)

(instance quest2Text of Actor
	(properties
		y 50
		x 244
		view 919
		loop 1
		illegalBits $0000
	)
)

(instance titleSpell of Actor
	(properties
		view 913
	)
)

(instance titleSpell2 of Actor
	(properties
		view 913
	)
)

(instance soYou of View
	(properties
		y 160
		x 50
		view 913
		loop 1
		priority 7
	)
)

(instance want of View
	(properties
		y 160
		x 127
		view 913
		loop 1
		cel 1
		priority 7
	)
)

(instance toBeA of View
	(properties
		y 160
		x 209
		view 913
		loop 1
		cel 2
		priority 7
	)
)

(instance heroWord of View
	(properties
		y 160
		x 286
		view 913
		loop 1
		cel 3
		priority 7
	)
)

(instance introMusic of Sound
	(properties)
)

(instance saurus of Actor
	(properties
		y 125
		x -20
		yStep 4
		view 430
		xStep 6
	)
)

(instance aHero of Actor
	(properties
		y 125
		x -80
		yStep 4
		view 5
		xStep 7
	)
)

(instance dragonHead of Prop
	(properties
		y 118
		x 170
		view 462
	)
)

(instance dragonTail of Prop
	(properties
		y 121
		x 87
		view 462
		loop 1
		priority 8
	)
)

(instance drTailScript of Script
	(properties)
	
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
