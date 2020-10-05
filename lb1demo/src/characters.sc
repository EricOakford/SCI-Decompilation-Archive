;;; Sierra Script 1.0 - (do not remove this comment)
(script# 778)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	characters 0
)

(local
	local0
	saveBits
	saveBits2
	saveBits3
	saveBits4
	windowX
	windowY
	aColonel
	aFifi
	aJeeves
	aCelie
	aGertie
	aRudy
	aGloria
	aEthel
	aLillian
	aClarence
	aWilbur
	aLaura
	local19
	charIcon
	theTheColonel_2
	theTheColonel_3
	local23 = [
		1 1 3 160 912 5 -6 -39 10 25 0 5 160 62 3 1 1 160 912 6 -1 -39
		5 15 0 4 252 912 7 -4 -42 5 15 1 4 72 913 1 -4 -38 5 15 8 5
		7 5 9 5 187 65 213 60 131 78 3 0 0 160 912 2 5 -46 5 15 0 3
		72 912 8 3 -39 5 15 0 2 252 912 9 -11 -34 5 15 2 5 4 6 3 18 80
		67 105 64 53 74 2 1 2 97 912 4 -3 -41 5 15 1 5 220 913 2 -3 -34
		5 15 10 5 1 5 238 75 265 60 2 0 1 240 912 3 -1 -41 5 15 1 0 77
		913 0 -4 -38 5 15 6 5 5 5 292 71 26 63 1 0 0 156 23 1 1 -36 5 15
		]
	playIndex
	feat1Index
	feat2Index
	feat3Index
	local185
	feat4Index
	castText = [
		778 0
		778 1
		778 2
		778 3
		778 4
		778 5
		778 6
		778 7
		778 8
		778 9
		778 10
		778 11
		]
	textIndex
)
(procedure (localproc_06ba)
	(= saveBits2
		(Display
			&rest
			105 4
			p_mode teJustCenter
			p_at 1 90
			p_width 320
			p_color vWHITE
			p_save
		)
	)
)

(procedure (localproc_06db)
	(= saveBits
		(Display
			&rest
			105 8
			p_mode teJustCenter
			p_at 1 165
			p_width 320
			p_color vWHITE
			p_save
		)
	)
)

(procedure (localproc_06fd)
	(Display 778 12 p_restore saveBits2)
)

(procedure (localproc_070c)
	(Display 778 12 p_restore saveBits)
)

(procedure (localproc_071b)
	(= saveBits3
		(Display
			&rest
			105 41
			p_mode teJustCenter
			p_at windowX windowY
			p_width 300
			p_color vBLACK
			p_save
		)
	)
)

(procedure (localproc_073c)
	(= saveBits4
		(Display
			&rest
			105 41
			p_mode teJustCenter
			p_at windowX windowY
			p_width 300
			p_color vWHITE
			p_save
		)
	)
)

(procedure (localproc_075e)
	(Display 778 12 p_restore saveBits3)
)

(procedure (localproc_076d)
	(Display 778 12 108 saveBits4)
)

(instance Colonel of Actor)

(instance Ethel of Actor)

(instance Gertie of Actor)

(instance Gloria of Actor)

(instance Rudy of Actor)

(instance Clarence of Actor)

(instance Wilbur of Actor)

(instance Lillian of Actor)

(instance Fifi of Actor)

(instance Jeeves of Actor)

(instance Celie of Actor)

(instance Laura of Actor)

(instance features1 of Prop)

(instance features2 of Prop)

(instance features3 of Prop)

(instance Mask1 of Prop)

(instance Mask2 of Prop)

(instance Mask3 of Prop)

(instance myMusic of Sound)

(instance characters of Room
	(properties
		picture 888
		style DISSOLVE
	)
	
	(method (init)
		(= currentPalette 1)
		(super init:)
		(HandsOff)
		(Load FONT 4)
		(Load FONT 8)
		(Load FONT 41)
		(Load SOUND 1)
		(Load VIEW 23)
		(Load VIEW 912)
		(Load VIEW 913)
		(= aColonel Colonel)
		(= aFifi Fifi)
		(= aJeeves Jeeves)
		(= aCelie Celie)
		(= aGertie Gertie)
		(= aRudy Rudy)
		(= aGloria Gloria)
		(= aEthel Ethel)
		(= aLillian Lillian)
		(= aClarence Clarence)
		(= aWilbur Wilbur)
		(= aLaura Laura)
		(Mask1
			view: 914
			loop: 0
			cel: 0
			y: 161
			setPri: 15
			init:
			stopUpd:
			hide:
		)
		(Mask2
			view: 914
			loop: 0
			cel: 0
			y: 161
			setPri: 15
			init:
			stopUpd:
			hide:
		)
		(Mask3
			view: 914
			loop: 0
			cel: 0
			y: 161
			setPri: 15
			init:
			stopUpd:
			hide:
		)
		(self setScript: showPlay)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(if
					(or
						(== (event message?) `S)
						(== (event message?) `s)
					)
					(curRoom newRoom: 779)
				)
			)
		)
	)
	
	(method (newRoom n)
		(= currentPalette 0)
		(super newRoom: n)
	)
)

(instance showPlay of Script
	
	(method (doit)
		(super doit:)
		(if (== (myMusic prevSignal?) -1)
			(curRoom newRoom: 779)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(myMusic number: 1 loop: -1 play:)
				(= windowX 11)
				(= windowY 17)
				(localproc_071b 778 13)
				(= windowX 10)
				(= windowY 15)
				(localproc_073c 778 13)
				(= seconds 5)
			)
			(1
				(localproc_076d)
				(localproc_075e)
				(= windowX 11)
				(= windowY 17)
				(localproc_071b 778 14)
				(= windowX 10)
				(= windowY 15)
				(localproc_073c 778 14)
				(= seconds 5)
			)
			(2
				(if (== local0 0)
					(localproc_076d)
					(localproc_075e)
					(= feat4Index 0)
					(= local19 (= playIndex (= textIndex -1)))
				)
				(if (== currentPalette 1)
					(= currentPalette 0)
					(DrawPic 888 DISSOLVE TRUE currentPalette)
				)
				(= charIcon [aColonel (++ local19)])
				(= local185 [local23 (++ playIndex)])
				(charIcon
					view: (if (== local0 5) 23 else 912)
					loop: [local23 (++ playIndex)]
					cel: [local23 (++ playIndex)]
					x: [local23 (++ playIndex)]
					y: 161
					init:
					stopUpd:
					setScript: Features1
					show:
				)
				(Mask1 x: [local23 playIndex])
				(= playIndex (+ playIndex 6))
				(if (>= local185 2)
					(= theTheColonel_2 [aColonel (++ local19)])
					(theTheColonel_2
						view: 912
						loop: [local23 (++ playIndex)]
						cel: [local23 (++ playIndex)]
						x: [local23 (++ playIndex)]
						y: 161
						init:
						stopUpd:
						setScript: Features2
						show:
					)
					(Mask2 x: [local23 playIndex])
					(= playIndex (+ playIndex 6))
				)
				(if (== local185 3)
					(= theTheColonel_3 [aColonel (++ local19)])
					(theTheColonel_3
						view: 912
						loop: [local23 (++ playIndex)]
						cel: [local23 (++ playIndex)]
						x: [local23 (++ playIndex)]
						y: 161
						init:
						stopUpd:
						setScript: Features3
						show:
					)
					(Mask3 x: [local23 playIndex])
					(= playIndex (+ playIndex 6))
				)
				(localproc_06ba
					[castText (++ textIndex)]
					[castText (++ textIndex)]
				)
				(localproc_06db
					[castText (++ textIndex)]
					[castText (++ textIndex)]
				)
				(if (== local0 5) (= state 6))
				(= seconds 5)
			)
			(3
				(localproc_070c)
				(localproc_06fd)
				(charIcon setScript: 0)
				(features1 setCycle: 0 hide:)
				(Mask1 show: setCycle: EndLoop self)
				(if (>= local185 2)
					(theTheColonel_2 setScript: 0)
					(features2 setCycle: 0 hide:)
					(Mask2 show: setCycle: EndLoop)
				)
				(if (== local185 3)
					(theTheColonel_3 setScript: 0)
					(features3 setCycle: 0 hide:)
					(Mask3 show: setCycle: EndLoop)
				)
			)
			(4
				(charIcon
					view: 914
					setLoop: 1
					setCel: [local23 (++ playIndex)]
				)
				(++ playIndex)
				(charIcon
					setStep: [local23 playIndex] [local23 playIndex]
				)
				(Mask1 setCycle: BegLoop self)
				(if (>= local185 2)
					(theTheColonel_2
						view: 914
						setLoop: 1
						setCel: [local23 (++ playIndex)]
					)
					(++ playIndex)
					(theTheColonel_2
						setStep: [local23 playIndex] [local23 playIndex]
					)
					(Mask2 setCycle: BegLoop)
				)
				(if (== local185 3)
					(theTheColonel_3
						view: 914
						setLoop: 1
						setCel: [local23 (++ playIndex)]
					)
					(++ playIndex)
					(theTheColonel_3
						setStep: [local23 playIndex] [local23 playIndex]
					)
					(Mask3 setCycle: BegLoop)
				)
			)
			(5
				(charIcon
					setMotion: MoveTo [local23 (++ playIndex)] [local23 (++ playIndex)] self
				)
				(Mask1 stopUpd: hide:)
				(if (>= local185 2)
					(theTheColonel_2
						setMotion: MoveTo [local23 (++ playIndex)] [local23 (++ playIndex)]
					)
					(Mask2 stopUpd: hide:)
				)
				(if (== local185 3)
					(theTheColonel_3
						setMotion: MoveTo [local23 (++ playIndex)] [local23 (++ playIndex)]
					)
					(Mask3 stopUpd: hide:)
				)
			)
			(6
				(charIcon stopUpd:)
				(if (>= local185 2) (theTheColonel_2 stopUpd:))
				(if (== local185 3) (theTheColonel_3 stopUpd:))
				(++ local0)
				(= state 1)
				(= cycles 7)
			)
			(7 (myMusic fade:))
		)
	)
)

(instance Features1 of Script
	(properties)
	
	(method (init)
		(= feat1Index playIndex)
		(super init:)
	)
	
	(method (changeState newState)
		(= feat4Index (^ feat4Index $0001))
		(switch (= state newState)
			(0
				(features1
					view: [local23 (++ feat1Index)]
					loop: [local23 (++ feat1Index)]
					x: (+ [local23 (- feat1Index 2)] [local23 (++ feat1Index)])
					y: (+ 161 [local23 (++ feat1Index)])
					setPri: 14
					ignoreActors: 1
					init:
				)
				(features1
					cel:
						(if (== local0 5)
							(= feat4Index 0)
						else
							(* (- (NumCels features1) 1) feat4Index)
						)
					cycleSpeed: 3
					setCycle: (if feat4Index BegLoop else EndLoop) self
					show:
				)
			)
			(1
				(= cycles
					(Random [local23 (++ feat1Index)] [local23 feat1Index])
				)
			)
			(2
				(features1
					loop: (if (== local0 5) 2 else (features1 loop?))
					setCycle:
						(if
						(== (features1 cel?) (- (NumCels features1) 1))
							BegLoop
						else
							EndLoop
						)
						self
				)
				(= state 0)
			)
		)
	)
)

(instance Features2 of Script
	(properties)
	
	(method (init)
		(= feat2Index playIndex)
		(super init:)
	)
	
	(method (changeState newState)
		(= feat4Index (^ feat4Index $0001))
		(switch (= state newState)
			(0
				(features2
					view: [local23 (++ feat2Index)]
					loop: [local23 (++ feat2Index)]
					x: (+ [local23 (- feat2Index 2)] [local23 (++ feat2Index)])
					y: (+ 161 [local23 (++ feat2Index)])
					setPri: 14
					ignoreActors: 1
					init:
				)
				(features2
					cel: (* (- (NumCels features2) 1) feat4Index)
					cycleSpeed: 3
					setCycle: (if feat4Index BegLoop else EndLoop) self
					show:
				)
			)
			(1
				(= cycles
					(Random [local23 (++ feat2Index)] [local23 feat2Index])
				)
			)
			(2
				(features2
					setCycle:
						(if
						(== (features2 cel?) (- (NumCels features2) 1))
							BegLoop
						else
							EndLoop
						)
						self
				)
				(= state 0)
			)
		)
	)
)

(instance Features3 of Script
	(properties)
	
	(method (init)
		(= feat3Index playIndex)
		(super init:)
	)
	
	(method (changeState newState)
		(= feat4Index (^ feat4Index $0001))
		(switch (= state newState)
			(0
				(features3
					view: [local23 (++ feat3Index)]
					loop: [local23 (++ feat3Index)]
					x: (+ [local23 (- feat3Index 2)] [local23 (++ feat3Index)])
					y: (+ 161 [local23 (++ feat3Index)])
					setPri: 14
					ignoreActors: 1
					init:
				)
				(features3
					cel: (* (- (NumCels features3) 1) feat4Index)
					cycleSpeed: 3
					setCycle: (if feat4Index BegLoop else EndLoop) self
					show:
				)
			)
			(1
				(= cycles
					(Random [local23 (++ feat3Index)] [local23 feat3Index])
				)
			)
			(2
				(features3
					setCycle:
						(if
						(== (features3 cel?) (- (NumCels features3) 1))
							BegLoop
						else
							EndLoop
						)
						self
				)
				(= state 0)
			)
		)
	)
)
