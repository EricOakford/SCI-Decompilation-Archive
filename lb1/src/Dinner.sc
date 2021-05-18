;;; Sierra Script 1.0 - (do not remove this comment)
(script# 209)
(include game.sh)
(use Main)
(use Intrface)
(use Path)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Dinner 0
)

(local
	saveBits
	saveBits2
	local2
	msgX
	msgY
	local5
	local6
	local7
	pathPts = [
		73 113
		50 149
		-20 149
		PATHEND
		]
)
(procedure (localproc_000c)
	(cast eachElementDo: #hide)
	(DrawPic 67 IRISOUT)
	(CHead setPri: 1 init:)
	(FHead init: setScript: eyeball)
	(Mouth init:)
	(Eye cycleSpeed: 5 setCycle: Forward init:)
	(Hand cycleSpeed: 5 init: setScript: handMotion)
)

(procedure (localproc_006b)
	(DrawPic 34 IRISIN)
	(addToPics doit:)
	(cast eachElementDo: #show)
	(CHead dispose:)
	(FHead dispose:)
	(Mouth dispose:)
	(Eye dispose:)
	(Hand dispose:)
)

(procedure (ForeDisplay)
	(= saveBits
		(Display &rest
			105 41
			p_mode teJustCenter
			p_at msgX msgY
			p_width 260
			p_color vWHITE
			p_save
		)
	)
)

(procedure (ForeClear)
	(Display 209 0 p_restore saveBits)
)

(procedure (BackDisplay)
	(= saveBits2
		(Display &rest
			105 41
			p_mode teJustCenter
			p_at msgX msgY
			p_width 260
			p_color vBLACK
			p_save
		)
	)
)

(procedure (BackClear)
	(Display 209 0 p_restore saveBits2)
)

(instance wOPath of Path
	(properties)
	
	(method (at n)
		(return [pathPts n])
	)
)

(instance Dinner of Room
	(properties
		picture 34
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(addToPics
			add:
				tableTop
				chute
				Ethel
				Gertie
				Gloria
				Rudy
				Jeeves
				Clarence
				Wilbur
				chair4
				chair5
				coffee
				chandelier
				flowers
			doit:
		)
		(Load PICTURE 67)
		(LoadMany VIEW 0 500 800)
		(Load FONT 41)
		(RHead setPri: 14 init: stopUpd:)
		(lHead setPri: 10 init: stopUpd:)
		(yHead setPri: 10 init: stopUpd:)
		(cHead setPri: 10 init: stopUpd:)
		(grHead setPri: 10 init: stopUpd:)
		(eHead setPri: 10 init: stopUpd:)
		(glHead setPri: 10 init: stopUpd:)
		(wHead setPri: 7 init: stopUpd:)
		(You init: stopUpd:)
		(Lilian init: stopUpd:)
		(if howFast
			(fire setCycle: Forward init:)
			(gas setPri: 9 setCycle: Forward init:)
		else
			(gas setPri: 9 init: stopUpd:)
			(fire init: stopUpd:)
		)
		(Colonel
			illegalBits: 0
			setCycle: Walk
			setPri: 10
			init:
			stopUpd:
		)
		(self setScript: speech)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript PATH)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(switch (event type?)
			(keyDown
				(if
					(or
						(== (event message?) `S)
						(== (event message?) `s)
						(== (event message?) ENTER)
						(== (event message?) SPACEBAR)
					)
					(Bset 50)
				)
			)
			(mouseDown
				(Bset fSkippedIntro)
			)
		)
		(return
			(if (Btst fSkippedIntro)
				(event claimed: TRUE)
				(curRoom newRoom: 44)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance speech of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (== (myMusic prevSignal?) -1) (== state 20))
			(Bset fSkippedIntro)
			(curRoom newRoom: 44)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(myMusic number: 4 loop: -1 play:)
				(= msgX 41)
				(= msgY 32)
				(BackDisplay 209 1)
				(= msgX 40)
				(= msgY 30)
				(ForeDisplay 209 1)
				(= seconds 8)
			)
			(2
				(ForeClear)
				(BackClear)
				(= cycles 1)
			)
			(3
				(Print 209 2 #at 104 10 #dispose)
				(Colonel setMotion: MoveTo 75 123 self)
				(cHead setCycle: EndLoop)
				(wHead setCycle: EndLoop)
				(eHead setCycle: EndLoop)
				(grHead setCycle: EndLoop)
				(RHead setCycle: EndLoop)
				(yHead setCycle: EndLoop)
				(lHead setCycle: EndLoop)
			)
			(4
				(cls)
				(localproc_000c)
				(Print 209 3 #at 10 117 #dispose)
				(= local7 5)
				(Mouth setScript: mouthCyc)
				(Eye loop: 2 setCycle: EndLoop)
				(= seconds 10)
			)
			(5
				(cls)
				(Eye setCycle: BegLoop)
				(= local7 6)
				(Mouth setScript: mouthCyc)
				(Print 209 4 #at 10 117 #dispose)
				(= seconds 10)
			)
			(6
				(cls)
				(Eye loop: 1 cycleSpeed: 6 setCycle: Forward)
				(= local7 5)
				(Mouth setScript: mouthCyc)
				(Print 209 5 #at 10 117 #dispose)
				(= seconds 10)
			)
			(7
				(cls)
				(= local7 3)
				(Mouth setScript: mouthCyc)
				(Print 209 6 #at 10 117 #dispose)
				(= seconds 5)
			)
			(8
				(cls)
				(= local7 1)
				(Mouth setScript: mouthCyc)
				(Print 209 7 #at 10 117 #dispose)
				(= seconds 3)
			)
			(9
				(cls)
				(= local7 5)
				(Mouth setScript: mouthCyc)
				(Print 209 8 #at 10 117 #dispose)
				(= seconds 10)
			)
			(10
				(cls)
				(Eye loop: 2 setCycle: EndLoop)
				(= local7 5)
				(Mouth setScript: mouthCyc)
				(Print 209 9 #at 10 117 #dispose)
				(= seconds 10)
			)
			(11
				(cls)
				(Eye setCycle: BegLoop)
				(= local7 3)
				(Mouth setScript: mouthCyc)
				(Print 209 10 #at 10 117 #dispose)
				(= seconds 5)
			)
			(12
				(cls)
				(= local7 1)
				(Mouth setScript: mouthCyc)
				(Print 209 11 #at 10 117 #dispose)
				(Hand setCycle: 0)
				(= seconds 5)
			)
			(13
				(cls)
				(localproc_006b)
				(Colonel
					setMotion: MoveTo (Colonel x?) (+ (Colonel y?) 20) self
				)
			)
			(14
				(Colonel setMotion: MoveTo -40 150 self)
			)
			(15
				(cls)
				(cHead setCycle: BegLoop)
				(wHead setCycle: BegLoop)
				(eHead setCycle: BegLoop)
				(grHead setCycle: BegLoop)
				(RHead setCycle: BegLoop)
				(yHead setCycle: BegLoop)
				(lHead setCycle: BegLoop)
				(= cycles 4)
			)
			(16
				(cls)
				(switch local2
					(0
						(Print 209 12 #at 73 145 #dispose)
						(yHead loop: 4 setCycle: EndLoop)
						(lHead loop: 9 setCycle: EndLoop)
						(grHead setCycle: EndLoop)
					)
					(1
						(Print 209 13 #at 110 28 #dispose)
						(wHead setCycle: BegLoop)
						(grHead setCycle: BegLoop)
						(eHead setCycle: EndLoop)
						(wHead loop: 1 setCycle: Forward)
					)
					(2
						(Print 209 14 #at 73 145 #dispose)
						(eHead setCycle: BegLoop)
						(RHead setCycle: EndLoop)
						(cHead loop: 8 setCycle: EndLoop)
						(wHead setCycle: 0)
					)
					(3
						(Print 209 15 #at 200 144 #width 75 #dispose)
						(RHead setCycle: BegLoop)
						(cHead setCycle: BegLoop)
						(grHead loop: 2 setCycle: EndLoop)
					)
					(4
						(Print 209 16 #at 149 15 #width 100 #dispose)
						(grHead setCycle: BegLoop)
						(wHead loop: 4 setCycle: EndLoop)
						(eHead loop: 3 setCycle: Forward)
					)
					(5
						(Print 209 17 #at 110 25 #dispose)
						(wHead setCycle: BegLoop)
						(eHead loop: 2 setCycle: EndLoop)
						(wHead loop: 1 setCycle: Forward)
					)
					(6
						(Print 209 18 #at 200 144 #width 100 #dispose)
						(eHead setCycle: BegLoop)
						(grHead setCycle: EndLoop)
						(wHead loop: 1 setCycle: 0)
					)
					(7
						(Print 209 19 #at 204 10 #width 95 #dispose)
						(grHead setCycle: BegLoop)
						(eHead loop: 1 setCycle: EndLoop)
						(glHead setCycle: Forward)
					)
					(8
						(Print 209 20 #at 149 15 #width 100 #dispose)
						(eHead setCycle: BegLoop)
						(wHead loop: 4 setCycle: EndLoop)
						(glHead setCycle: 0)
						(eHead loop: 3 setCycle: Forward)
					)
					(9
						(Print 209 21 #at 144 147 #dispose)
						(wHead setCycle: BegLoop)
						(grHead setCycle: EndLoop)
						(eHead setCycle: 0)
					)
					(10
						(Print 209 22 #at 224 15 #width 75 #dispose)
						(grHead setCycle: BegLoop)
						(eHead loop: 1 setCycle: EndLoop)
						(glHead setCycle: Forward)
					)
				)
				(= seconds 3)
			)
			(17
				(cls)
				(glHead setCycle: 0)
				(if (!= local2 10)
					(++ local2)
					(= state 15)
					(= cycles 1)
				else
					(yHead setCycle: BegLoop self)
				)
			)
			(18
				(chair addToPic:)
				(yHead loop: 3 setCycle: EndLoop)
				(cHead loop: 7 setCycle: EndLoop)
				(wHead loop: 5 setCycle: EndLoop)
				(eHead loop: 2 setCycle: EndLoop)
				(grHead loop: 1 setCycle: EndLoop)
				(RHead setCycle: EndLoop)
				(lHead dispose:)
				(Lilian
					startUpd:
					view: 800
					loop: 4
					cel: 0
					x: 100
					y: 118
					illegalBits: 0
					ignoreActors: TRUE
					setCycle: Forward
				)
				(Print 209 23 #at 40 18 #dispose)
				(= seconds 5)
			)
			(19
				(cls)
				(Lilian
					view: 500
					loop: 2
					setCycle: Walk
					setMotion: (Clone wOPath)
				)
				(chair1 addToPic:)
				(yHead hide:)
				(You
					view: 0
					loop: 2
					x: 124
					y: 118
					startUpd:
					setCycle: Walk
					illegalBits: 0
					ignoreActors: TRUE
					setMotion: (Clone wOPath) self
				)
			)
			(20
				(myMusic fade:)
			)
			(21
				(client setScript: 0)
				(curRoom newRoom: 44)
			)
		)
	)
)

(instance eyeball of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FHead setCycle: EndLoop)
				(= seconds (Random 1 6))
			)
			(1
				(= state -1)
				(= seconds (Random 1 6))
			)
		)
	)
)

(instance handMotion of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Hand setCycle: EndLoop)
				(= seconds (Random 1 6))
			)
			(1
				(= state -1)
				(= seconds (Random 1 6))
			)
		)
	)
)

(instance mouthCyc of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: EndLoop self)
			)
			(1
				(if (== local7 (++ local6))
					(= local6 0)
					(client cel: 0 setScript: 0)
				else
					(= state -1)
					(= cycles 1)
				)
			)
		)
	)
)

(instance tableTop of PicView
	(properties
		y 107
		x 151
		view 167
		loop 6
		priority 9
	)
)

(instance chute of PicView
	(properties
		y 121
		x 23
		view 134
		loop 2
	)
)

(instance Wilbur of PicView
	(properties
		y 118
		x 152
		view 421
		priority 7
	)
)

(instance Jeeves of PicView
	(properties
		y 105
		x 175
		view 450
		priority 7
	)
)

(instance Clarence of PicView
	(properties
		y 135
		x 132
		view 401
		cel 5
		priority 10
	)
)

(instance Ethel of PicView
	(properties
		y 118
		x 178
		view 321
		cel 2
		priority 7
	)
)

(instance Gertie of PicView
	(properties
		y 135
		x 161
		view 341
		cel 1
		priority 10
	)
)

(instance Gloria of PicView
	(properties
		y 119
		x 218
		view 361
		cel 1
		priority 8
	)
)

(instance Rudy of PicView
	(properties
		y 135
		x 199
		view 381
		cel 1
		priority 10
	)
)

(instance chair4 of PicView
	(properties
		y 90
		x 205
		view 134
		loop 6
		cel 1
		priority 5
	)
)

(instance chair5 of PicView
	(properties
		y 90
		x 50
		view 134
		loop 6
		priority 5
	)
)

(instance coffee of PicView
	(properties
		y 138
		x 301
		view 134
		priority 12
	)
)

(instance chandelier of PicView
	(properties
		y 42
		x 144
		view 134
		cel 3
		priority 9
	)
)

(instance flowers of PicView
	(properties
		y 55
		x 129
		view 134
		cel 2
		priority 9
	)
)

(instance yHead of Prop
	(properties
		y 82
		x 123
		view 2
		loop 3
	)
)

(instance lHead of Prop
	(properties
		y 82
		x 99
		view 501
		loop 8
	)
)

(instance cHead of Prop
	(properties
		y 101
		x 135
		view 401
		loop 7
	)
)

(instance grHead of Prop
	(properties
		y 101
		x 163
		view 341
		loop 1
	)
)

(instance RHead of Prop
	(properties
		y 99
		x 201
		view 381
		loop 7
	)
)

(instance glHead of Prop
	(properties
		y 84
		x 221
		view 361
		loop 1
	)
)

(instance eHead of Prop
	(properties
		y 83
		x 180
		view 321
		loop 2
		priority 10
	)
)

(instance wHead of Prop
	(properties
		y 83
		x 152
		view 421
		loop 8
		priority 10
	)
)

(instance fire of Prop
	(properties
		y 85
		x 131
		view 232
		cel 1
		priority 5
	)
)

(instance Colonel of Actor
	(properties
		y 150
		x -10
		view 306
	)
)

(instance You of Actor
	(properties
		y 118
		x 124
		view 2
	)
)

(instance Lilian of Actor
	(properties
		y 118
		x 100
		view 501
		cel 1
	)
)

(instance chair of Prop
	(properties
		y 114
		x 94
		view 134
		loop 5
		cel 4
		priority 6
	)
)

(instance chair1 of Prop
	(properties
		y 116
		x 118
		view 134
		loop 5
		cel 3
		priority 6
	)
)

(instance myMusic of Sound
	(properties)
)

(instance gas of Prop
	(properties
		y 82
		x 24
		view 134
		loop 3
		cel 1
		priority 9
	)
)

(instance CHead of Prop
	(properties
		y 96
		x 173
		view 167
		priority 1
	)
)

(instance FHead of Prop
	(properties
		y 39
		x 109
		view 167
		loop 5
		cel 1
		priority 1
	)
)

(instance Eye of Prop
	(properties
		y 85
		x 180
		view 167
		loop 1
	)
)

(instance Mouth of Prop
	(properties
		y 106
		x 177
		view 167
		loop 3
	)
)

(instance Hand of Prop
	(properties
		y 147
		x 186
		view 167
		loop 4
		priority 1
	)
)
