;;; Sierra Script 1.0 - (do not remove this comment)
(script# INTRO) ;1
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	intro 0
)

(define MaxSmokes	10)
(define MaxCarps	11)

(local
	armSpeed
	targState
	smokeCycles
	lookDir
	lookLoop = [6 2 6]
	lookCel = [0 2 1]
	fireData = [4 62 120 3 63 140 2 80 135 5 97 140 4 111 128 4 143 127 4 225 121 2 232 135 3 246 139 5 269 141]
	[theSmoke MaxSmokes]
	carpData = [0 167 141 1 176 136 2 178 132 3 180 128 4 180 123 5 180 118 4 182 115 3 184 112 2 187 109 1 195 105 0 208 102]
)
(instance intro of Room
	(properties
		picture rHalfDome
		style DISSOLVE
	)
	
	(method (init)
		(LoadMany PICTURE pBlack rDistantCity)
		(LoadMany VIEW rHalfDome vHalfGenie vCarpet rTitleScreen rDistantCity)
		(LoadMany SOUND rHalfDome sGenieHand1 sGenieHand2 sGenieClaps sGenieLaughs sTitleFlame)
		(super init:)
		(self setScript: halfD)
	)
)

(instance head of Actor
	(properties
		x 151
		y 128
		view vHalfGenie
		loop 2
		priority 2
		signal (| fixedCel ignrHrz ignrAct fixedLoop fixPriOn) ;$7810
		illegalBits NULL
	)
)

(instance body of Actor
	(properties
		x 149
		y 145
		view vHalfGenie
		loop 4
		priority 1
		signal (| fixedCel ignrHrz ignrAct fixedLoop fixPriOn) ;$7810
		illegalBits NULL
	)
)

(instance arms of Actor
	(properties
		x 152
		y 73
		view vHalfGenie
		loop 7
		priority 3
		signal (| fixedCel ignrHrz ignrAct fixedLoop fixPriOn) ;$7810
		illegalBits NULL
	)
)

(instance hand1 of Actor
	(properties
		x 114
		y 76
		view vHalfGenie
		priority 15
		signal (| fixedCel ignrHrz ignrAct fixedLoop fixPriOn) ;$7810
		illegalBits NULL
	)
)

(instance hand2 of Actor
	(properties
		x 175
		y 80
		view vHalfGenie
		loop 1
		priority 15
		signal (| fixedCel ignrHrz ignrAct fixedLoop fixPriOn) ;$7810
		illegalBits NULL
	)
)

(instance carpet of Actor
	(properties
		x -10
		y 100
		yStep 3
		view vCarpet
		priority 15
		signal (| ignrAct ignrHrz fixPriOn) ;$6010
		cycleSpeed 1
		illegalBits NULL
	)
)

(instance sierra of View
	(properties
		x 188
		y 173
		view rHalfDome
		priority 12
		signal (| fixedCel ignrHrz ignrAct fixedLoop fixPriOn) ;$7810
	)
)

(instance presents of View
	(properties
		x 190
		y 187
		view rHalfDome
		loop 1
		priority 12
		signal (| fixedCel ignrHrz ignrAct fixedLoop fixPriOn) ;$7810
	)
)

(instance halfD of Script

	(method (doit &tmp carpetX oldDir)
		(super doit:)
		(if (== state 19)
			(= carpetX (carpet x?))
			(= oldDir lookDir)
			(cond 
				((< carpetX 120) (= lookDir 0))
				((> carpetX 250) (self cue:))
				((> carpetX 190) (= lookDir 2))
				(else (= lookDir 1))
			)
			(if (!= oldDir lookDir)
				(head setLoop: [lookLoop lookDir] setCel: [lookCel lookDir])
			)
		)
	)
	
	(method (changeState newState &tmp smoker smokeIndex genieJump)
		(switch (= state newState)
			(0
				(globalSound number: rHalfDome loop: 1 playBed:)
				(= seconds 3)
			)
			(1
				(hand1 cel: 0 init: cycleSpeed: 2 setCycle: EndLoop self)
			)
			(2
				(miscSound number: sGenieHand1 loop: 0 priority: 8 play:)
				(ShakeScreen 3)
				(= seconds 2)
			)
			(3
				(hand1 stopUpd:)
				(hand2 cel: 0 init: cycleSpeed: 2 setCycle: EndLoop self)
			)
			(4
				(miscSound number: sGenieHand2 loop: 0 priority: 8 play:)
				(hand2 stopUpd:)
				(ShakeScreen 3)
				(= seconds 2)
			)
			(5
				(switch howFast
					(slow
						(= armSpeed 1)
						(= genieJump 28)
					)
					(medium
						(= armSpeed 2)
						(= genieJump 14)
					)
					(else 
						(= armSpeed 3)
						(= genieJump 7)
					)
				)
				(head init: yStep: genieJump setMotion: MoveTo 152 80)
				(body yStep: genieJump init: setMotion: MoveTo 152 96 self)
			)
			(6
				(body setMotion: MoveTo 152 86)
				(head setMotion: MoveTo 152 70)
				(arms setCel: 0 init:)
				(= cycles armSpeed)
			)
			(7
				(arms setCel: 1)
				(body posn: 152 80)
				(head posn: 152 64)
				(= cycles armSpeed)
			)
			(8
				(arms setCel: 2 setPri: 13)
				(body posn: 151 72)
				(head posn: 151 56)
				(hand1 dispose:)
				(hand2 dispose:)
				(= cycles armSpeed)
			)
			(9
				(arms setCel: 3)
				(body posn: 150 63)
				(head posn: 150 47)
				(= cycles armSpeed)
			)
			(10 (= seconds 2))
			(11
				(body addToPic:)
				(head stopUpd:)
				(arms setLoop: 5 setCel: 0)
				(= cycles 1)
			)
			(12
				(arms setCel: 1)
				(= cycles 1)
			)
			(13
				(miscSound number: sGenieClaps loop: 0 priority: 8 play: self)
				(arms setCel: 2)
			)
			(14
				(arms setCel: 3)
				(= cycles 1)
			)
			(15
				(miscSound number: sGenieClaps loop: 0 priority: 8 play: self)
				(arms setCel: 4)
			)
			(16
				(sierra init: addToPic:)
				(presents init: addToPic:)
				(= seconds 2)
			)
			(17
				(arms setLoop: 7 setCel: 3 addToPic:)
				(= seconds 2)
			)
			(18
				(head setCel: 1)
				(= lookDir 2)
				(carpet init: setScript: hdCarp)
				(= cycles 1)
			)
			(19 (= lookDir 1))
			(20
				(head setLoop: 2 setCel: 2 setCycle: BegLoop self)
			)
			(21
				(miscSound number: sGenieLaughs loop: 0 priority: 8 play:)
				(self setScript: genLaugh self)
			)
			(22
				(head addToPic:)
				(self dispose:)
			)
		)
	)
)

(instance genLaugh of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(head
					setLoop: 3
					setCel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(= register 5)
			)
			(1
				(head setCel: 1)
				(= cycles 2)
			)
			(2
				(head setCel: 2)
				(= cycles 2)
			)
			(3
				(if (-- register)
					(self changeState: 1)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance hdCarp of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(carpet setCycle: Forward setMotion: MoveTo 340 92 self)
			)
			(1
				(curRoom setScript: titles)
			)
		)
	)
)

(instance questWord of PicView
	(properties
		x 72
		y 64
		view rTitleScreen
		priority 5
	)
)

(instance forWord of PicView
	(properties
		x 161
		y 62
		view rTitleScreen
		cel 1
		priority 5
	)
)

(instance gloryWord of PicView
	(properties
		x 248
		y 66
		view rTitleScreen
		cel 2
		priority 5
	)
)

(instance twoWord of PicView
	(properties
		x 154
		y 115
		view rTitleScreen
		cel 3
		priority 0
	)
)

(instance trial of PicView
	(properties
		x 155
		y 175
		view rTitleScreen
		loop 1
		priority 5
	)
)

(instance bigF of Prop
	(properties
		x 210
		y 171
		view rTitleScreen
		loop 6
		priority 6
		signal $6810
		cycleSpeed 1
	)
)

(instance titles of Script

	(method (changeState newState &tmp smoker smokeIndex)
		(switch (= state newState)
			(0
				(cast eachElementDo: #dispose eachElementDo: #delete)
				(curRoom drawPic: pBlack)
				(questWord init:)
				(forWord init:)
				(gloryWord init:)
				(twoWord init:)
				(trial init:)
				(addToPics doit:)
				(= smoker 0)
				(= smokeIndex 0)
				(while (< smoker MaxSmokes)
					((= [theSmoke smoker] (Prop new:))
						view: rTitleScreen
						loop: [fireData smokeIndex]
						cel: (mod smoker 4)
						posn: [fireData (+ smokeIndex 1)] [fireData (+ smokeIndex 2)]
						setPri: 3
						ignoreActors:
						init:
						stopUpd:
					)
					(if (>= howFast 2)
						([theSmoke smoker] setCycle: Forward)
						(= smokeCycles 1)
					else
						([theSmoke smoker] addToPic:)
						(= smokeCycles 0)
					)
					(++ smoker)
					(= smokeIndex (+ smokeIndex 3))
				)
				(= seconds 2)
			)
			(1
				(carpet
					posn: -20 108
					init:
					setCycle: Forward
					setMotion: MoveTo 167 157 self
				)
			)
			(2
				(if smokeCycles
					(= smoker 0)
					(= smokeIndex 0)
					(while (< smoker 10)
						([theSmoke smoker] setCycle: 0 stopUpd:)
						(++ smoker)
						(= smokeIndex (+ smokeIndex 3))
					)
				)
				(miscSound number: sTitleFlame loop: 0 priority: 10 play:)
				(bigF init: setCycle: Forward)
				(carpet setLoop: 1)
				(self setScript: carpetDodge self)
			)
			(3
				(bigF dispose:)
				(if smokeCycles
					(= smoker 0)
					(= smokeIndex 0)
					(while (< smoker 10)
						([theSmoke smoker] setCycle: Forward)
						(++ smoker)
						(= smokeIndex (+ smokeIndex 3))
					)
				)
				(carpet
					setPri: 1
					setLoop: 0
					setCycle: Forward
					setMotion: MoveTo 340 162 self
				)
			)
			(4
				(curRoom setScript: cityScript)
			)
		)
	)
)

(instance carpetDodge of Script
	
	(method (changeState newState &tmp index)
		(if (< (= state newState) MaxCarps)
			(= index (* state 3))
			(carpet
				setCel: [carpData index]
				setMotion: MoveTo [carpData (+ index 1)] [carpData (+ index 2)] self
			)
		else
			(self dispose:)
		)
	)
)

(instance shapeir of PicView
	(properties
		x 145
		y 90
		view rDistantCity
		loop 2
		priority 0
		signal fixPriOn
	)
)

(instance saurusLot of PicView
	(properties
		x 123
		y 94
		view rDistantCity
		loop 3
		priority 1
		signal fixPriOn
	)
)

(instance cityScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cast eachElementDo: #dispose eachElementDo: #delete)
				(curRoom drawPic: rDistantCity)
				(addToPics
					add: shapeir saurusLot
					eachElementDo: #init
					doit:
				)
				(= seconds 2)
			)
			(1
				(Print INTRO 0
					#width 300
					#at -1 5
					#dispose
				)
				(if (!= howFast slow)
					(carpet cycleSpeed: 1)
				)
				(carpet
					posn: -20 146
					setPri: 15
					init:
					setCycle: Forward
					setMotion: MoveTo 180 160 self
				)
			)
			(2
				(carpet setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3
				(carpet
					cycleSpeed: (+ 1 (carpet cycleSpeed?))
					setLoop: 2
					cel: 0
					setCycle: CycleTo 4 1
					setMotion: MoveTo 150 120 self
				)
			)
			(4
				(carpet setCycle: EndLoop setMotion: MoveTo 190 100 self)
			)
			(5
				(carpet
					cycleSpeed: (+ 1 (carpet cycleSpeed?))
					setMotion: MoveTo 139 85 self
				)
			)
			(6
				(carpet hide:)
				(= cycles 2)
			)
			(7
				(cls)
				(globalSound fade:)
				(curRoom newRoom: INN)
			)
		)
	)
)
