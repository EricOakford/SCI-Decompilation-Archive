;;; Sierra Script 1.0 - (do not remove this comment)
(script# ENDGAME)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Path)
(use Save)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	EndGame 0
)

(local
	local0
	[local29 29] = [208 57 199 52 194 48 198 47 202 45 212 44 220 46 227 49 238 52 249 49 261 47 272 45 288 43 294 40 -32768]
	[local44 15] = [270 48 256 53 239 60 226 65 216 70 201 74 184 76 -32768]
	[local55 11] = [90 144 106 151 124 156 141 159 160 162 -32768]
	[local74 19] = [193 162 214 156 240 148 263 140 282 131 297 119 309 109 318 100 332 90 -32768]
	[local82 8] = [140 145 122 122 137 127 129 139]
	local83
	local84
	local85
	theCycles =  10
	theCycles_2 =  10
	[local88 7] = [3 3 3 2 160 120]
)
(procedure (localproc_000c)
	(if modelessDialog (modelessDialog dispose:))
	(cast eachElementDo: #dispose 84)
	(switch (curRoom script?)
		(awardScript
			(curRoom setScript: heroScript)
		)
		(heroScript
			(curRoom setScript: bigScript)
		)
		(bigScript
			(curRoom setScript: flyScript)
		)
		(flyScript
			(curRoom setScript: dragScript)
		)
	)
)

(procedure (ToCharSave)
	(SetCursor theCursor TRUE)
	(DisposeScript PATH)
	(curRoom newRoom: CHARSAVE)
)

(procedure (PrintEnd fg bg)
	(if (>= numColors 8)
		(creditWindow color: fg back: bg)
	)
	(Print &rest #mode teJustCenter #dispose #window creditWindow)
)

(instance creditWindow of SysWindow
	(properties)
)

(instance endStatus of Code
	(properties)
	
	(method (doit strg)
		(Format strg 600 0 score)
	)
)

(instance baron of Prop
	(properties
		y 111
		x 157
		view vEndGameCast
		loop 3
		cel 1
	)
)

(instance bug1 of Prop
	(properties
		y 188
		x 251
		view vEndGameCast
		loop 5
	)
)

(instance bug2 of Prop
	(properties
		y 188
		x 61
		view vEndGameCast
		loop 6
	)
)

(instance yoric of Actor
	(properties
		y 134
		x -20
		view vEndGameCast
		loop 7
	)
)

(instance jr of PicView
	(properties
		y 106
		x 193
		view vEndGameCast
		loop 1
		cel 3
	)
)

(instance elsa of PicView
	(properties
		y 108
		x 119
		view vEndGameCast
		loop 1
		cel 2
	)
)

(instance p1 of PicView
	(properties
		y 117
		x 157
		view vEndGameCast
		loop 8
	)
)

(instance p1Mirror of PicView
	(properties
		y 117
		x 157
		view vEndGameCast
		loop 9
	)
)

(instance p2 of PicView
	(properties
		y 111
		x 96
		view vEndGameCast
		loop 1
	)
)

(instance p3 of PicView
	(properties
		y 119
		x 220
		view vEndGameCast
		loop 2
		cel 5
	)
)

(instance p4 of PicView
	(properties
		y 123
		x 84
		view vEndGameCast
		loop 2
		cel 7
	)
)

(instance p5 of PicView
	(properties
		y 123
		x 241
		view vEndGameCast
		loop 2
		cel 8
	)
)

(instance p6 of PicView
	(properties
		y 146
		x 301
		view vEndGameCast
		loop 2
		cel 10
	)
)

(instance p7 of PicView
	(properties
		y 158
		x 311
		view vEndGameCast
		loop 2
		cel 11
	)
)

(instance p8 of PicView
	(properties
		y 138
		x 29
		view vEndGameCast
		loop 2
	)
)

(instance p9 of PicView
	(properties
		y 187
		x 306
		view vEndGameCast
		loop 2
		cel 1
	)
)

(instance p10 of PicView
	(properties
		y 151
		x 291
		view vEndGameCast
		loop 2
		cel 3
	)
)

(instance p11 of PicView
	(properties
		y 116
		x 260
		view vEndGameCast
		loop 2
		cel 4
	)
)

(instance p12 of PicView
	(properties
		y 164
		x 16
		view vEndGameCast
		loop 2
		cel 6
	)
)

(instance p13 of PicView
	(properties
		y 181
		x 26
		view vEndGameCast
		loop 2
		cel 9
	)
)

(instance p14 of PicView
	(properties
		y 119
		x 81
		view vEndGameCast
		loop 2
		cel 2
	)
)

(instance Ger of PicView
	(properties
		y 122
		x 62
		view vEndGameCast
		cel 1
	)
)

(instance Jer of PicView
	(properties
		y 114
		x 238
		view vEndGameCast
		cel 2
	)
)

(instance Kenn of PicView
	(properties
		y 114
		x 280
		view vEndGameCast
		cel 3
	)
)

(instance Lar of PicView
	(properties
		y 143
		x 314
		view vEndGameCast
		cel 4
	)
)

(instance Jeff of PicView
	(properties
		y 115
		x 253
		view vEndGameCast
		cel 5
	)
)

(instance Bob of PicView
	(properties
		y 119
		x 294
		view vEndGameCast
		cel 6
	)
)

(instance Cindy of PicView
	(properties
		y 121
		x 45
		view vEndGameCast
		cel 7
	)
)

(instance Lori of PicView
	(properties
		y 182
		x 295
		view vEndGameCast
		cel 8
	)
)

(instance Cori of PicView
	(properties
		y 126
		x 16
		view vEndGameCast
	)
)

(instance Mark of PicView
	(properties
		y 125
		x 30
		view vEndGameCast
		cel 9
	)
)

(instance hero of View
	(properties
		y 189
		x 157
		view vEgoLarge
	)
)

(instance sparkle of Prop
	(properties
		y 138
		x 142
		view vEgoLarge
		loop 1
	)
)

(instance lightning of Prop
	(properties
		view vEgoLarge
		loop 6
	)
)

(instance hairL of Prop
	(properties
		y 79
		x 131
		view vEgoLarge
		loop 2
	)
)

(instance hairR of Prop
	(properties
		y 69
		x 138
		view vEgoLarge
		loop 3
	)
)

(instance hairM of Prop
	(properties
		y 92
		x 168
		view vEgoLarge
		loop 4
	)
)

(instance cape of Prop
	(properties
		y 144
		x 198
		view vEgoLarge
		loop 5
	)
)

(instance bck of Actor
	(properties
		y 182
		x -50
		yStep 1
		view vEgoFlyingCarpet
		loop 8
		xStep 2
	)
)

(instance mid of Actor
	(properties
		y 182
		x -17
		yStep 1
		view vEgoFlyingCarpet
		loop 8
		cel 1
		xStep 2
	)
)

(instance frt of Actor
	(properties
		y 180
		x 16
		yStep 1
		view vEgoFlyingCarpet
		loop 8
		cel 2
		xStep 2
	)
)

(instance carpet of Actor
	(properties)
)

(instance upPath of Path
	(properties)
	
	(method (at param1)
		(return [local29 param1])
	)
)

(instance wizTurn of Path
	(properties)
	
	(method (at param1)
		(return [local44 param1])
	)
)

(instance fortPath of Path
	(properties)
	
	(method (at param1)
		(return [local55 param1])
	)
)

(instance awayPath of Path
	(properties)
	
	(method (at param1)
		(return [local74 param1])
	)
)

(instance glimmer of Prop
	(properties
		y 151
		x 238
		view vEgoFlyingCarpet
		loop 4
	)
)

(instance shadow of Prop
	(properties
		view vEgoFlyingCarpet
	)
	
	(method (doit)
		(super doit:)
		(self
			loop:
			(switch (carpet loop?)
				(0 5)
				(1 6)
				(2 6)
				(3 7)
			)
			setPri: (carpet priority?)
			posn: (carpet x?) (+ (carpet y?) 16)
		)
		(if local83
			(= local83 0)
			(self cel: (mod (+ (self cel?) 1) 5))
		else
			(= local83 1)
		)
	)
)

(instance bigCarpet of Actor
	(properties)
)

(instance ii of View
	(properties
		y 143
		x 236
		view vQFG2
		cel 3
	)
)

(instance trial of Prop
	(properties
		y 170
		x 155
		view vTrialByFire
		loop 1
	)
)

(instance quest of Prop
	(properties
		y 55
		x 183
		view vQFG2
	)
)

(instance forProp of Prop
	(properties
		y 92
		x 163
		view vQFG2
		cel 1
	)
)

(instance glory of Prop
	(properties
		y 97
		x 247
		view vQFG2
		cel 2
	)
)

(instance claw1 of Prop
	(properties
		y 92
		x 51
		view vDragonHead
		priority 3
	)
)

(instance claw2 of Prop
	(properties
		y 87
		x 127
		view vDragonHead
		loop 1
		cel 1
		priority 3
	)
)

(instance head of Prop
	(properties
		y 49
		x 87
		view vDragonHead
		loop 2
	)
)

(instance flame of Actor
	(properties
		view vDragonFire
	)
)

(instance EndGame of Room
	(properties
		picture 39
		style $0001
	)
	
	(method (init)
		(DisposeScript 298)
		(DisposeScript 297)
		(DisposeScript 205)
		(DisposeScript 206)
		(DisposeScript 204)
		(Load SOUND (SoundFX 99))
		(LoadMany PICTURE 148 906 101 750)
		(LoadMany VIEW vEgoWithMedal vEgoFlyingCarpet vEgoLarge vTrialByFire vQFG2 vDragonHead vDragonFire)
		(SolvePuzzle POINTS_WINGAME 25)
		(StatusLine code: endStatus enable:)
		(super init: &rest)
		(cSound stop:)
		(DoSound SoundOn TRUE)
		(theGame setSpeed: 6)
		(keyDownHandler eachElementDo: #dispose #delete)
		(keyDownHandler add: self)
		(HandsOff)
		(= [invNum iGold] (+ [invNum iGold] 110))
		(= [invNum iGold] (+ [invNum iGold] 30))
		(self setScript: awardScript)
	)
	
	(method (dispose)
		(StatusLine code: dftStatusCode)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(event claimed: TRUE)
		(if (== (event type?) keyDown)
			(switch (event message?)
				(`#2
					(ToggleSound)
				)
				(`#5
					(theGame save:)
				)
			)
		)
	)
)

(instance awardScript of Script
	(properties)
	
	(method (init)
		(elsa init:)
		(addToPics add: elsa)
		(if (Btst SAVED_BARNARD) (jr init:) (addToPics add: jr))
		(p1 priority: 6)
		(p1Mirror priority: 6)
		(p2 cel: (if (Btst DEFEATED_MINOTAUR) 0 else 1))
		(addToPics
			add:
				Mark Lori Cori Cindy Bob Jeff Lar Kenn Jer Ger
				p1 p1Mirror p2 p3 p4 p5 p6 p7 p8 p9 p10 p11 p12
				p13 p14
			eachElementDo: #init
			doit:
		)
		(super init: &rest)
		(NormalEgo)
		(ego posn: 160 215 init:)
	)
	
	(method (doit)
		(switch (cSound prevSignal?)
			(10 (= local0 3))
			(20 (= local0 9))
		)
		(if (and (> local0 state) (or seconds cycles))
			(= seconds (= cycles 0))
			(self cue:)
		else
			(super doit:)
		)
	)
	
	(method (changeState newState &tmp theDetailLevel)
		(if (> (= theDetailLevel howFast) medium)
			(-- theDetailLevel)
		)
		(switch (= state newState)
			(0
				(= local0 0)
				(baron init:)
				(bug1 init:)
				(bug2 init:)
				(= cycles 1)
			)
			(1
				(SetCursor theCursor FALSE)
				(ego setMotion: MoveTo 160 184)
				(= seconds 3)
			)
			(2
				(cSound number: (SoundFX 99) loop: 1 play:)
				(if (!= howFast slow)
					(bug1 cycleSpeed: theDetailLevel setCycle: EndLoop)
				else
					(bug1 cel: 2)
				)
				(= seconds 4)
			)
			(3
				(bug1 stopUpd:)
				(if (!= howFast slow)
					(bug2 cycleSpeed: theDetailLevel setCycle: EndLoop self)
				else
					(bug2 cel: 2)
					(= cycles 1)
				)
			)
			(4
				(bug2 stopUpd:)
				(= seconds 5)
			)
			(5
				(yoric
					init:
					cycleSpeed: theDetailLevel
					moveSpeed: theDetailLevel
					setCycle: Forward
					setLoop: 7
					setStep: 10 1
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 340 134
				)
				(= seconds 6)
			)
			(6
				(ego setMotion: MoveTo 160 120)
				(= seconds 4)
			)
			(7
				(baron cycleSpeed: 2 setCycle: EndLoop)
				(= seconds 4)
			)
			(8
				(ego
					view: vEndGameCast
					setLoop: 4
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(= seconds 12)
			)
			(9 (localproc_000c))
		)
	)
)

(instance post1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(if (and (Btst fBabaFrog) (Btst SAVED_BARNARD))
					(PrintEnd vBLACK vLGREY 600 1 #at 2 30 #width 84 #time 14)
					; Thus the hero from the East
					; Freed the man from form of beast
					; Saved beauty from the brigand's band
					; And forced the Ogress to flee the land.
					
				else
					(PrintEnd vBLACK vLGREY 600 2 #at 2 30 #width 84 #time 14)
					; You have defeated the brigands
					; And become a true Hero of Spielburg
				)
			)
		)
	)
)

(instance post2 of Script
	(properties)
	
	(method (dispose)
		(if (>= state 1) (local85 dispose:))
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(if (Btst fBabaFrog)
					(= local85
						(PrintEnd vYELLOW vLBLUE 600 3 #at 224 70 #width 80 #time 14)
						; The brigand band has been dispersed
						; Their treasure has been reimbursed.
					)
				else
					(= local85
						(PrintEnd vYELLOW vLBLUE 600 4 #at 224 70 #width 80 #time 10)
						; So with the Kattas and the Merchant
						; You board a magic carpet to the land of Shapeir.
					)
				)
				(= modelessDialog 0)
				(= seconds 5)
			)
			(2
				(if (Btst fBabaFrog)
					(PrintEnd vYELLOW vLBLUE 600 5 #at 6 14 #width 80 #time 16)
					; And so with Kattas and Abdulla Doo
					; You bid the valley a fond adieu.
				else
					(PrintEnd vYELLOW vLBLUE 600 6 #at 6 14 #width 100 #time 23)
					; Unfortunately, since the Baron is still
					; cursed, and Baba Yaga remains to work her evil deeds, terror will continue to rule the land.
				)
				(= seconds 8)
			)
			(3 (self dispose:))
		)
	)
)

(instance heroScript of Script
	(properties)
	
	(method (init)
		(sparkle setCycle: Forward cycleSpeed: 3)
		(lightning setCycle: Forward cycleSpeed: 0)
		(hairL setCycle: Forward cycleSpeed: 1)
		(hairR setCycle: Forward cycleSpeed: 1)
		(hairM setCycle: Forward cycleSpeed: 1)
		(cape setCycle: Forward cycleSpeed: 2)
		(hero setPri: 3 init: addToPic:)
		(sparkle setPri: 5 init:)
		(lightning setPri: 2 init:)
		(hairL setPri: 5 init:)
		(hairR setPri: 5 init:)
		(hairM setPri: 5 init:)
		(cape setPri: 2 init:)
		(= local84 55)
		(super init: &rest)
		(StatusLine disable:)
		(curRoom style: 0 drawPic: 148)
		(self setScript: post1)
	)
	
	(method (doit)
		(if (== (cSound prevSignal?) 30) (localproc_000c))
		(super doit:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= local0 0)
				(= temp0 (* (Random 0 3) 2))
				(sparkle
					setCycle: EndLoop self
					x: [local82 temp0]
					y: [local82 (+ temp0 1)]
				)
				(lightning
					setCycle: EndLoop
					cycleSpeed: 0
					x: (Random 70 245)
					y: (Random 25 45)
				)
			)
			(1
				(lightning cel: 0 setCycle: 0)
				(sparkle cel: 0 setCycle: 0)
				(if local84
					(-- local84)
					(self changeState: 0)
				else
					(localproc_000c)
				)
			)
		)
	)
)

(instance bigScript of Script
	(properties)
	
	(method (init &tmp temp0 temp1)
		(bck init:)
		(mid init:)
		(frt init:)
		(= temp0
			(switch howFast
				(slow 4)
				(medium 3)
				(else  2)
			))
		(= temp1
			(switch howFast
				(slow 2)
				(else  1)
			))
		(bck setStep: temp0 temp1)
		(mid setStep: temp0 temp1)
		(frt setStep: temp0 temp1)
		(super init: &rest)
		(curRoom style: IRISOUT drawPic: 750)
		(self setScript: post2)
	)
	
	(method (doit &tmp temp0)
		(if (== (cSound prevSignal?) 40) (localproc_000c))
		(if (= temp0 (Random 0 2))
			(mid y: (- (+ (bck y?) temp0) 1))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bck
					ignoreActors:
					setLoop: 8
					setCel: 0
					setMotion: MoveTo 340 22
				)
				(mid
					ignoreActors:
					setLoop: 8
					setCel: 1
					setMotion: MoveTo 373 22
				)
				(frt
					ignoreActors:
					setLoop: 8
					setCel: 2
					setMotion: MoveTo 406 20
				)
			)
		)
	)
)

(instance flyScript of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(carpet
			view: vEgoFlyingCarpet
			setLoop: 0
			setCel: 0
			setStep: 1 1
			posn: 208 57
			ignoreActors: 1
			init:
		)
		(glimmer cycleSpeed: 1 setCycle: Forward init:)
		(shadow init:)
		(curRoom style: 1 drawPic: 101)
	)
	
	(method (doit)
		(if (== (cSound prevSignal?) 50)
			(localproc_000c)
		else
			(super doit:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(carpet
					setStep: 2 2
					setScript: cornyCredits
					setMotion: upPath self
				)
			)
			(1
				(carpet
					setLoop: 1
					cel: 0
					setCycle: 0
					setMotion: MoveTo 305 39 self
				)
			)
			(2
				(carpet cel: 1 setPri: 8 setMotion: MoveTo 311 42 self)
			)
			(3
				(carpet cel: 2 setMotion: MoveTo 300 52 self)
			)
			(4
				(carpet cel: 3 setMotion: MoveTo 291 56 self)
			)
			(5
				(carpet setMotion: wizTurn self)
			)
			(6
				(carpet setMotion: MoveTo 169 81 self)
			)
			(7
				(carpet setMotion: MoveTo 155 85 self)
			)
			(8
				(carpet cel: 4 setMotion: MoveTo 135 83 self)
			)
			(9
				(carpet cel: 5 setMotion: MoveTo 128 74 self)
			)
			(10
				(carpet cel: 6 setMotion: MoveTo 138 71 self)
			)
			(11
				(carpet setMotion: MoveTo 149 68 self)
			)
			(12
				(carpet cel: 7 setMotion: MoveTo 165 64 self)
			)
			(13
				(carpet cel: 8 setMotion: MoveTo 181 66 self)
			)
			(14
				(carpet cel: 9 setMotion: MoveTo 192 70 self)
			)
			(15
				(carpet cel: 10 setMotion: MoveTo 182 76 self)
			)
;			;CI: NOTE: This case already exists, and will probably never be executed
;			(13
;				(carpet cel: 11 setMotion: MoveTo 168 88 self)
;			)
			;CI: NOTE: This case already exists, and will probably never be executed
;			(14
;				(carpet cel: 12 setMotion: MoveTo 158 100 self)
;			)
			;CI: NOTE: This case already exists, and will probably never be executed
;			(15
;				(carpet cel: 13 setMotion: MoveTo 140 107 self)
;			)
			(16
				(carpet cel: 14 setMotion: MoveTo 130 115 self)
			)
			(17
				(carpet setPri: 0 setMotion: MoveTo 115 122 self)
			)
			(18
				(carpet setMotion: MoveTo 99 127 self)
			)
			(19
				(carpet setMotion: MoveTo 88 131 self)
			)
			(20
				(carpet setLoop: 2 cel: 0 setMotion: MoveTo 69 127 self)
			)
			(21
				(carpet setMotion: MoveTo 57 114 self)
			)
			(22
				(carpet setPri: 8 setMotion: MoveTo 46 106 self)
			)
			(23
				(carpet cel: 1 setMotion: MoveTo 49 119 self)
			)
			(24
				(carpet setMotion: MoveTo 60 130 self)
			)
			(25
				(carpet setMotion: MoveTo 70 138 self)
			)
			(26
				(carpet cel: 2 setMotion: fortPath self)
			)
			(27
				(creditWindow color: 9 back: 1)
				(carpet
					setLoop: 3
					cel: 0
					cycleSpeed: 2
					setCycle: Forward
					setMotion: awayPath
				)
			)
		)
	)
)

(instance dragScript of Script
	(properties)
	
	(method (doit)
		(switch (cSound prevSignal?)
			(60 (= local0 1))
			(70 (= local0 3))
			(80 (= local0 7))
			(90 (= local0 8))
		)
		(if (not (cSound loop?)) (= local0 13))
		(if (and (> local0 state) (or seconds cycles))
			(= seconds (= cycles 0))
			(self cue:)
		else
			(super doit:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 0)
				(curRoom drawPic: 906)
				(bigCarpet
					init:
					view: vTrialByFire
					setLoop: 0
					posn: -10 100
					setStep: 3 3
					moveSpeed: 0
					cycleSpeed: 1
					setCycle: Forward
					setMotion: MoveTo 340 92
				)
				(= seconds 10)
			)
			(1
				(claw1 cel: 0 init: cycleSpeed: 2 setCycle: EndLoop self)
			)
			(2
				(ShakeScreen 3)
				(= seconds 10)
			)
			(3
				(claw1 stopUpd: addToPic:)
				(claw2 cel: 0 init: cycleSpeed: 2 setCycle: EndLoop)
				(= cycles theCycles_2)
			)
			(4
				(claw2 stopUpd: addToPic:)
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
			(5
				(head
					setLoop: 3
					cel: 0
					posn: 87 49
					init:
					setCycle: CycleTo 4 1
				)
				(= seconds 10)
			)
			(6
				(quest init: addToPic:)
				(forProp init: addToPic:)
				(glory init: addToPic:)
				(= seconds 20)
			)
			(7
				(ii init: addToPic:)
				(= seconds 10)
			)
			(8 (head setCycle: CycleTo 5 1 self))
			(9
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
					setMotion: MoveTo 196 228
					setCycle: EndLoop self
				)
			)
			(10
				(trial init: cycleSpeed: 0 setCycle: EndLoop self)
				(flame
					setLoop: 1
					cel: 0
					cycleSpeed: (if (== howFast slow) 0 else 1)
					setMotion: MoveTo 196 228
					setCycle: Forward
				)
				(head setCycle: EndLoop)
			)
			(11
				(trial setLoop: 2 setCycle: Forward)
				(self cue:)
			)
			(12 (= seconds 50))
			(13 (ToCharSave))
		)
	)
)

(instance cornyCredits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 10))
			(1
				(PrintEnd vBLUE vYELLOW 600 7 #at 40 30 #width 120 #time 6)
				;Script and Whip: Lori Ann Cole
				(= seconds 6)
			)
			(2
				(PrintEnd vYELLOW vBROWN 600 8 #at 185 135 #width 120 #time 6)
				;Vicious Art: Kenn Nishiuye
				(= seconds 6)
			)
			(3
				(PrintEnd vLCYAN vCYAN 600 9 #at 50 70 #width 110 #time 3)
				;Silly Stuff: Jeff Crowe
				(= seconds 3)
			)
			(4
				(PrintEnd vLMAGENTA vBLUE 600 10 #at 178 132 #width 110 #time 5)
				;Next Generation Graphics: Jerry Moore
				(= seconds 5)
			)
			(5
				(PrintEnd vBLACK vLGREY 600 11 #at 80 130 #width 130 #time 6)
				;Marathon Coding and Rude Puns: Bob Fischbach
				(= seconds 7)
			)
			(6
				(PrintEnd vLGREEN vGREEN 600 12 #at 30 100 #width 130 #time 5)
				;Algorithmic Gyrations: Larry Scott
				(= seconds 5)
			)
			(7
				(PrintEnd vLMAGENTA vBLUE 600 13 #at 160 22 #width 110 #time 5)
				;Cameo Coding: Jerry Shaw
				(= seconds 6)
			)
			(8
				(PrintEnd vBLUE vYELLOW 600 14 #at 40 30 #width 110 #time 5)
				;Insidious System Software: Corey Cole
				(= seconds 6)
			)
			(9
				(PrintEnd vLGREEN vGREEN 600 15 #at 80 50 #width 130 #time 6)
				;Magical Mystical Music: Mark Seibert
				(= seconds 7)
			)
			(10
				(PrintEnd vYELLOW vBROWN 600 16 #at 40 32 #width 120 #time 6)
				;Underground Art: Cindy Walker
				(= seconds 7)
			)
			(11
				(PrintEnd vLCYAN vCYAN 600 17 #at 130 30 #width 140 #time 6)
				;Yogic Sympathy and Support: Guruka Singh Khalsa
				(= seconds 7)
			)
			(12
				(PrintEnd vLCYAN vBLUE 600 18 #at -1 26 #width 260 #time 15)
				;Promotional Considerations:
				;Erasmus' Rootin' Tootin' Root Beer
				;Spielburg Chamber of Commerce
				;Shapeir Magic Carpet Tours
				;Famous Adventurer's Correspondence School for Heroes
			)
		)
	)
)
