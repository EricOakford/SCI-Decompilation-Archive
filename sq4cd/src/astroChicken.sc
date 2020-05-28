;;; Sierra Script 1.0 - (do not remove this comment)
(script# 290)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Dialog)
(use Sq4Feature)
(use Block)
(use LoadMany)
(use Sound)
(use Motion)
(use System)

(public
	astro 0
)

(local
	local0 =  1
	numEggs
	pointsBits
	farmerTimer
	local4
	local5
	local6
	local7
	local8
	currentPoints
	messageBits
	highScore
	oldVolume
	eggBits
	scoreBits
	[local15 6] = [0 5 4 3 2 1]
	[local21 6] = [0 9 8 7 6 5]
	local27 =  50
	local28 =  1
	newSq4Actor
	oldTime
	theGameTime_2
	local32
	killedCedric
)
(instance theSound of Sound
	(properties)
)

(instance winged of Sound
	(properties
		number 56
	)
)

(instance eggSquirt of Sound
	(properties
		number 122
	)
)

(instance eggFall of Sound
	(properties
		number 131
	)
)

(instance eggSplatting of Sound
	(properties
		number 147
	)
)

(instance gunshot of Sound
	(properties
		number 144
	)
)

(instance cornPicker of Sound
	(properties
		number 146
	)
)

(instance dogBarking of Sound
	(properties
		number 827
	)
)

(instance astro of SQRoom
	(properties)
	
	(method (init)
		(= oldTime gameTime)
		(LoadMany VIEW 290 291)
		(Load PICTURE 291)
		(LoadMany SOUND 55 140 56 122 131 147 144 146 827)
		(self drawPic: 290 overlay: 291 0 setScript: startScript)
		(user canControl: TRUE canInput: TRUE)
		(super init:)
		(= picture 290)
		(= oldVolume (theGame masterVolume:))
		(musicToggle init:)
		(mouseDownHandler add: cast features)
		(theIconBar disable:)
		(sq4 setCursor: 991)
		(ego init: hide:)
		(self setRegions: MALL)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (< (Abs (- gameTime theGameTime_2)) 6)
			0
		else
			(= theGameTime_2 gameTime)
			(Palette PALCycle 48 55 1)
		)
		(if
			(and
				(> (Abs (- gameTime oldTime)) 2400)
				(not (astroChicken script?))
				(not (astroIndicator script?))
				local0
			)
			(= oldTime gameTime)
			(astroIndicator setScript: sellOutScript)
		)
		(cond 
			((curRoom script?))
			((not (-- local27))
				(= local27 50)
				(if (not (cast contains: henHouseRight))
					(if (not (cast contains: rock))
						(rock init:)
						(if
							(and
								(not (Random 0 20))
								(not (cast contains: cedric))
								(not killedCedric)
							)
							(cedric
								view: 272
								setLoop: 0
								setCel: 0
								posn: 360 (Random 20 30)
								setPri: 13
								init:
								scrollSpeed: (+ (/ (- 15 speed) 4) 1 1)
								cycleSpeed: 6
								setCycle: Forward
							)
						)
					)
					(if (not (cast contains: grass)) (grass init:))
				)
			)
		)
		(cond 
			((curRoom script?))
			(local4
				(if (and (not (-- local4)) local5)
					((cornStalk new:) init:)
					(= local4 (* 3 (+ (/ speed 3) 1)))
					(-- local5)
				)
			)
			(
				(and
					(>= currentPoints 300)
					(not (cast contains: crazedFarmer))
					(not (cast contains: windMill))
					(not (cast contains: weasel))
					(not (cast contains: rabidDog))
					(not (cast contains: astroChicken2))
				)
				(henHouseLeft init:)
				(henHouseRight init:)
				(astroChicken2
					loop: (astroChicken loop?)
					x: (astroChicken x?)
					y: (astroChicken y?)
					init:
					setCycle: Forward
				)
				(astroChicken hide: dispose:)
				(curRoom setScript: doHenHouse)
			)
			(
				(or
					(cast contains: hill)
					(cast contains: crazedFarmer)
					(cast contains: windMill)
					(cast contains: weasel)
					(cast contains: chickenWire)
					(cast contains: henHouseRight)
				)
			)
			(
			(or (sounds contains: winged) (sounds contains: 55)))
			((<= (= temp0 (Random 1 100)) 15)
				(hill init:)
				(switch (Random 0 2)
					(0
						(if (not (cast contains: rabidDog)) (rabidDog init:))
					)
					(1
						(if (not (cast contains: crazedFarmer))
							(crazedFarmer init:)
						)
					)
				)
			)
			((<= temp0 30) (crazedFarmer init:))
			((<= temp0 40) (windMill init:))
			((<= temp0 65)
				(if
					(and
						(< (astroChicken eggsLeft?) 5)
						(not (cast contains: cornStalk))
					)
					(= local4 3)
					(= local5 (Random 2 8))
				)
			)
			((<= temp0 80) (if (== local28 1) (= local28 0) else (weasel init:)))
			(
				(and
					(<= temp0 85)
					(not (astroChicken loop?))
					(not (cast contains: chickenWire))
				)
				(chickenWire init:)
			)
			((not (cast contains: rabidDog)) (rabidDog init:))
		)
	)
	
	(method (dispose)
		(mouseDownHandler delete: cast features)
		(theIconBar enable:)
		(theGame masterVolume: oldVolume)
		(DisposeScript 949)
		(super dispose:)
	)
	
	(method (newRoom)
		(if numEggs (DoDisplay numEggs))
		(if pointsBits (DoDisplay pointsBits))
		(if messageBits (DoDisplay messageBits))
		(if highScore (DoDisplay highScore))
		(if eggBits (DoDisplay eggBits))
		(if scoreBits (DoDisplay scoreBits))
		(super newRoom: &rest)
	)
)

(class astroChicken of Sq4Actor
	(properties
		view 290
		signal (| fixedLoop fixPriOn)		
		livesLeft 3
		eggsLeft 10
		wireLeft 0
	)
	
	(method (init &tmp [str 5])
		(music number: 54 loop: -1 vol: 127 play:)
		(super init:)
		(self
			moveSpeed: (+ (/ speed 2) 1)
			show:
			x: 160
			y: 40
			eggsLeft: 10
			loop: 0
			wireLeft: 0
			showEggs:
			showPts:
		)
		(astroIndicator init:)
		(directionHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(if eggBits (DoDisplay eggBits))
		(if scoreBits (DoDisplay scoreBits))
		(= eggBits
			(DoDisplay {Eggs:} #at 150 178 #color myTextColor6)
		)
		(= scoreBits
			(DoDisplay {Score:} #at 240 178 #color myTextColor6)
		)
		(if pointsBits (DoDisplay pointsBits))
		(= pointsBits
			(DoDisplay
				(Format @str {%d} currentPoints)
				#at 275 178
				#color myTextColor6
				#width 25
			)
		)
	)
	
	(method (dispose)
		(directionHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0 temp1)
		(if (user canControl:)
			(cond 
				((& (event type?) direction)
					(event claimed: TRUE)
					(switch (event message?)
						(dirW
							(astroChicken setMotion: MoveTo 0 (astroChicken y?))
						)
						(dirE
							(astroChicken setMotion: MoveTo 320 (astroChicken y?))
						)
						(dirN
							(astroChicken setMotion: MoveTo (astroChicken x?) 0)
						)
						(dirS
							(astroChicken setMotion: MoveTo (astroChicken x?) 190)
						)
					)
				)
				((== (event type?) mouseDown)
					(event claimed: TRUE)
					(if (== (event modifiers?) shiftDown)
						(if
							(and
								eggsLeft
								(not (cast contains: egg))
								(astroChicken isNotHidden:)
							)
							(eggSquirt play:)
							(egg init:)
							(-- eggsLeft)
							(self showEggs:)
						)
					else
						(= temp0 (Min 213 (Max 87 (event x?))))
						(astroChicken setMotion: MoveTo temp0 (event y?))
					)
				)
				((== (event type?) keyDown)
					(event claimed: TRUE)
					(if
						(and
							(== (event message?) ENTER)
							eggsLeft
							(not (cast contains: egg))
							(astroChicken isNotHidden:)
						)
						(eggSquirt play:)
						(egg init:)
						(-- eggsLeft)
						(self showEggs:)
					)
				)
			)
		else
			(event claimed: TRUE)
		)
	)
	
	(method (die)
		(if (not (-- livesLeft))
			(HandsOff)
			(SQ4Print 290 0)
			(if modelessDialog (modelessDialog dispose:))
			(curRoom newRoom: 376)
		else
			(winged dispose:)
			(cast eachElementDo: #dispose)
			(curRoom drawPic: 290 style: -32762)
			(self view: 290 setCycle: Forward init:)
			(= local28 1)
			(astroIndicator
				cel: (- (astroIndicator cel?) 1)
				init:
				show:
			)
			(user canControl: TRUE canInput: TRUE)
		)
	)
	
	(method (showEggs &tmp [str 5])
		(if numEggs (DoDisplay numEggs))
		(= numEggs
			(DoDisplay
				(Format @str {%d} eggsLeft)
				#at 180 178
				#color myTextColor6
				#width 25
			)
		)
	)
	
	(method (showPts &tmp [str 5])
		(if pointsBits (DoDisplay pointsBits))
		(= pointsBits
			(DoDisplay
				(Format @str {%d} currentPoints)
				#at 275 178
				#color myTextColor6
				#width 25
			)
		)
	)
	
	(method (getEgg &tmp temp0)
		(++ eggsLeft)
		(= temp0 (Max 0 (- eggsLeft 8)))
		(self showEggs:)
	)
	
	(method (incScore param1 &tmp [temp0 41] temp41)
		(if (> param1 1)
			(= currentPoints (+ currentPoints param1))
		else
			(= temp41 (/ y 20))
			(if param1
				(= currentPoints (+ currentPoints [local15 temp41]))
			else
				(= currentPoints (+ currentPoints [local21 temp41]))
			)
		)
		(self showPts:)
	)
)

(class ScrollActor of Sq4Actor
	(properties
		view 290
		priority 12
		signal (| fixedLoop fixPriOn)		
		scrollSpeed 4
		deathLoop 0
		deathMusic 0
	)
	
	(method (init)
		(self scrollSpeed: (+ (/ (- 15 speed) 4) 1))
		(= nsRight (= nsBottom (= nsLeft (= nsTop 0))))
		(super init:)
		(= nsLeft -1)
		(= x 350)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((< (= x (- x scrollSpeed)) -50) (self dispose:))
			(
				(and
					(astroChicken isNotHidden:)
					(self onMe: astroChicken)
				)
				(self doChicken:)
			)
			((and (cast contains: egg) (self onMe: egg)) (self doEgg:))
		)
	)
	
	(method (cue)
		(astroChicken die:)
	)
	
	(method (doChicken)
		(if (and (not (astroChicken script?)) deathLoop)
			(music stop:)
			(eggSplatting stop:)
			(theSound number: deathMusic loop: 1 play: self)
			(astroChicken hide:)
			(self
				setLoop: deathLoop
				setCel: 0
				cycleSpeed: 18
				setCycle: EndLoop
			)
		)
	)
	
	(method (doEgg)
	)
)

(instance cedric of ScrollActor
	(properties
		x 560
		y 20
		view 272
		priority 13
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(self onMe: astroChicken)
				(not (astroChicken script?))
			)
			(astroChicken setScript: killCedricScript)
		)
	)
	
	(method (cue)
	)
	
	(method (doChicken)
	)
)

(instance hill of ScrollActor
	(properties
		y 143
		loop 11
		cel 4
	)
	
	(method (init)
		(self setCel: (Random 4 5))
		(super init:)
	)
)

(instance grass of ScrollActor
	(properties
		loop 11
		cel 6
	)
	
	(method (init)
		(super init:)
		(= scrollSpeed (+ (/ (- 15 speed) 4) 1 1))
		(= x (+ x (Random 0 150)))
		(= y 155)
		(self setPri: 14)
	)
)

(instance rock of ScrollActor
	(properties
		y 143
		loop 11
		cel 7
		priority 13
	)
	
	(method (init)
		(super init:)
		(= scrollSpeed (+ (/ (- 15 speed) 4) 1 1))
		(= x (+ x (Random 0 150)))
		(= y 155)
		(self setPri: 14)
	)
)

(instance weasel of ScrollActor
	(properties
		illegalBits $0000
		deathLoop 7
		deathMusic 55
	)
	
	(method (init)
		(super init:)
		(= scrollSpeed (+ (/ (- 15 speed) 4) 1 1))
		(= loop 4)
		(= y (astroChicken y?))
		(self setCycle: Forward)
	)
	
	(method (onMe event &tmp temp0)
		(= temp0 0)
		(cond 
			(
				(not
					(if
						(and
							(<= nsLeft (event x?))
							(<= (event x?) (- nsRight 15))
							(<= (- nsTop 5) (event y?))
						)
						(<= (event y?) (+ nsBottom 10))
					)
				)
			)
			((== scrollSpeed (+ (/ (- 15 speed) 4) 1)))
			((astroChicken loop?)
				(astroChicken
					wireLeft: (- (astroChicken wireLeft?) 1)
					incScore: 5
				)
				(weasel scrollSpeed: (+ (/ (- 15 speed) 4) 1))
				(if (not (astroChicken wireLeft?))
					(astroChicken setLoop: 0)
				)
			)
			(else (= temp0 1))
		)
		(return temp0)
	)
)

(instance crazedFarmer of ScrollActor
	(properties
		loop 5
	)
	
	(method (init)
		(= loop 5)
		(if (cast contains: hill) (= y 130) else (= y 143))
		(super init:)
		(= farmerTimer 10)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((== loop 8))
			(
				(and
					(not cycler)
					(astroChicken isNotHidden:)
					(not (-- farmerTimer))
				)
				(self setCycle: EndLoop self)
			)
		)
		(if
			(and
				(== loop 5)
				(<= x (astroChicken x?))
				(astroChicken isNotHidden:)
			)
			(self setLoop: 6)
		)
	)
	
	(method (onMe event)
		(return
			(if
				(and
					(<= nsLeft (event x?))
					(<= (event x?) nsRight)
					(<= nsTop (event y?))
				)
				(<= (event y?) (- nsBottom 15))
			else
				0
			)
		)
	)
	
	(method (cue)
		(gunshot play:)
		((buckShot new:) init:)
		(self setCycle: BegLoop)
		(= farmerTimer (Random (+ speed 5) (+ speed 10)))
	)
	
	(method (doEgg)
		(astroChicken incScore: 0)
		(eggFall stop:)
		(eggSplatting play:)
		(egg hide: dispose:)
		(self setLoop: 8 setCel: 0 setCycle: EndLoop)
	)
)

(instance windMill of ScrollActor
	(properties
		loop 11
	)
	
	(method (init)
		(super init:)
		(= y 105)
		(self setPri: 13)
		(windMillBlade init:)
	)
)

(instance windMillBlade of ScrollActor
	(properties
		deathLoop 13
		deathMusic 55
	)
	
	(method (init)
		(= loop 12)
		(super init:)
		(= y 105)
		(self setPri: 14 setCycle: Forward)
	)
)

(instance cornStalk of ScrollActor
	(properties
		y 120
		loop 11
		cel 1
	)
	
	(method (init)
		(super init:)
	)
	
	(method (onMe event)
		(return
			(if
				(and
					(<= (- nsLeft 1) (event x?))
					(<= (event x?) (+ nsRight 2))
					(<= (- nsTop 10) (event y?))
				)
				(<= (event y?) (+ nsBottom 15))
			else
				0
			)
		)
	)
	
	(method (doChicken)
		(if (== cel 1)
			(cornPicker play:)
			(astroChicken getEgg:)
			(self setCel: 2)
		)
	)
)

(instance rabidDog of ScrollActor
	(properties
		deathLoop 10
		deathMusic 140
	)
	
	(method (init)
		(= loop 15)
		(= cel 0)
		(= cycleSpeed 6)
		(if (cast contains: hill) (= y 130) else (= y 143))
		(super init:)
	)
	
	(method (onMe event)
		(return
			(if (== event astroChicken)
				(return
					(if
						(and
							(<= (- nsLeft 15) (event x?))
							(<= (event x?) (- nsRight 25))
							(<= (- nsTop 75) (event y?))
						)
						(<= (event y?) nsBottom)
					else
						0
					)
				)
			else
				(super onMe: event &rest)
			)
		)
	)
	
	(method (cue)
		(return 0)
	)
	
	(method (doChicken)
		(if
			(and
				(== loop 15)
				(not (astroChicken script?))
				(not cycler)
			)
			(dogBarking play:)
			(if (< (- y (astroChicken y?)) 50)
				(= local32 1)
				(theSound number: deathMusic loop: 1 play: self)
				(curRoom setScript: dogDieScript)
			else
				(self setCycle: EndLoop)
			)
		)
	)
	
	(method (doEgg)
		(if local32
			(egg hide: dispose:)
			(= local32 0)
		else
			(astroChicken incScore: 1)
			(eggFall stop:)
			(eggSplatting play:)
			(egg hide: dispose:)
			(self setLoop: 9 setCel: 0 setCycle: EndLoop)
		)
	)
)

(instance chickenWire of ScrollActor
	(properties
		y 150
		loop 11
		cel 13
	)
	
	(method (init)
		(self setCel: 13)
		(super init:)
	)
	
	(method (doChicken)
		(astroChicken setLoop: 1 wireLeft: 3)
		(self setCel: 14)
	)
)

(instance theCage of Cage
	(properties
		top 20
		left 75
		bottom 118
		right 225
	)
)

(instance astroIndicator of Sq4Prop
	(properties
		x 16
		y 185
		view 290
		loop 11
		cel 12
		priority 15
		signal fixPriOn
	)
)

(instance egg of Sq4Actor
	(properties
		view 290
		priority 13
		signal (| fixedLoop fixPriOn)
	)
	
	(method (init)
		(self moveSpeed: speed)
		(super init:)
		(eggFall play:)
		(= x (astroChicken x?))
		(= y (+ (astroChicken y?) 5))
		(= local7 7)
		(= local8 1)
		(= local6 2)
		(self setLoop: 2 setCycle: Forward)
	)
	
	(method (doit)
		(super doit:)
		(self posn: (+ x local7) (+ y local8))
		(if (not (-- local6))
			(-- local7)
			(++ local8)
			(if (>= y 150)
				((eggSplat new:) init:)
				(self dispose:)
			else
				(= local6 2)
			)
		)
	)
)

(instance chickenHead of Sq4Actor
	(properties
		view 271
		loop 3
		priority 13
		signal (| fixedLoop fixPriOn)
		cycleSpeed 12
		moveSpeed 1
	)
	
	(method (init)
		(super init:)
		(self
			posn: (astroChicken x?) (astroChicken y?)
			setCycle: Forward
			setMotion: MoveTo (astroChicken x?) 300
		)
	)
	
	(method (doit)
		(super doit:)
		(self posn: (+ x local7) (+ y local8))
		(if (not (-- local6))
			(-- local7)
			(++ local8)
			(if (>= y 150)
				((eggSplat new:) init:)
				(self dispose:)
			else
				(= local6 2)
			)
		)
	)
)

(instance fallingMs of Sq4Actor
	(properties
		view 290
		priority 1
		signal (| fixedLoop fixPriOn)
		moveSpeed 1
	)
	
	(method (init)
		(super init:)
		(eggFall play:)
		(= x (astroChicken x?))
		(= y (astroChicken y?))
		(= view (astroChicken view?))
		(= loop (astroChicken loop?))
		(= cel (astroChicken cel?))
		(self setStep: 0 10 ignoreActors: 1)
	)
)

(instance sellOutProp of Sq4Actor
	(properties
		x 350
		y 25
		priority 14
		moveSpeed 2
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(astroChicken isNotHidden:)
				(not (astroChicken script?))
				(astroChicken onMe: self)
			)
			(curRoom setScript: propDieScript self)
			(self dispose:)
		)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance eggSplat of Sq4Actor
	(properties
		y 155
		view 290
		loop 3
		priority 12
		signal (| fixedLoop fixPriOn)
		xStep 4
	)
	
	(method (init)
		(super init:)
		(eggFall stop:)
		(eggSplatting play:)
		(= x (egg x?))
		(self
			xStep: (+ (/ (- 15 speed) 4) 1)
			moveSpeed: 2
			setCycle: EndLoop
			setMotion: MoveTo -5 y self
		)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance buckShot of Sq4Actor
	(properties
		yStep 9
		view 290
		loop 11
		cel 3
		signal (| ignrAct ignrHrz fixedLoop)
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(= farmerTimer 0)
		(self
			moveSpeed: speed
			posn: (crazedFarmer x?) 108
			setMotion: MoveTo (crazedFarmer x?) -18 self
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(cedric onMe: self)
				(not (cedric loop?))
				(not killedCedric)
			)
			(cedric cycleSpeed: 12 setLoop: 1 setCel: 0 setCycle: EndLoop)
			(= killedCedric TRUE)
		)
		(if
			(and
				(astroChicken isNotHidden:)
				(astroChicken onMe: self)
				(not (astroChicken script?))
			)
			(curRoom setScript: buckShotDieScript self)
			(self dispose:)
		)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance henHouseLeft of Sq4Actor
	(properties
		x 500
		y 145
		view 290
		loop 11
		cel 8
		priority 3
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (> x 169) (= x (- x 9)) else (= x 160))
	)
)

(instance henHouseRight of Sq4Actor
	(properties
		x 500
		y 145
		view 290
		loop 11
		cel 9
		priority 12
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (> x 169) (= x (- x 9)) else (= x 160))
	)
)

(instance backok of Sq4Prop
	(properties
		x 100
		y 90
		view 290
		loop 14
	)
)

(instance astroChicken2 of Sq4Actor
	(properties
		view 290
		signal fixedLoop
	)
)

(instance musicToggle of Sq4Feature
	(properties
		x 82
		y 177
		nsTop 176
		nsLeft 81
		nsBottom 188
		nsRight 117
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(or
					(== (event type?) mouseDown)
					(and
						(== (event type?) keyDown)
						(== (event message?) KEY_PAUSE)
					)
				)
				(self onMe: event)
			)
			(if (theGame masterVolume:)
				(theGame masterVolume: 0)
			else
				(theGame masterVolume: oldVolume)
			)
			(event claimed: TRUE)
		)
	)
)

(instance buckShotDieScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(user canControl: FALSE canInput: FALSE)
				(music stop:)
				(eggFall stop:)
				(theSound number: 55 loop: 1 play:)
				(astroChicken
					setMotion: 0
					view: 291
					setLoop: 0
					setCycle: EndLoop self
				)
			)
			(1 (= seconds 2))
			(2
				(astroChicken hide:)
				(fallingMs
					init:
					setMotion: MoveTo (astroChicken x?) 220 self
				)
			)
			(3
				(eggFall stop:)
				(eggSplatting play:)
				(ShakeScreen 2)
				(= ticks 10)
			)
			(4
				(ShakeScreen 3)
				(= seconds 2)
			)
			(5
				(eggSplatting stop:)
				(winged play: astroChicken)
				(fallingMs dispose:)
				(astroChicken die:)
				(self dispose:)
			)
		)
	)
)

(instance propDieScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(user canControl: FALSE canInput: FALSE)
				(music stop:)
				(eggFall stop:)
				(theSound number: 55 loop: 1 play:)
				(astroChicken
					setMotion: 0
					view: 271
					setLoop: 2
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(astroChicken hide:)
				(= seconds 6)
				(chickenHead init:)
			)
			(2
				(chickenHead dispose:)
				(astroChicken die:)
				(self dispose:)
			)
		)
	)
)

(instance dogDieScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(music stop:)
				(eggSplatting stop:)
				(astroChicken hide:)
				(rabidDog
					setLoop: 10
					setCel: 0
					cycleSpeed: 18
					setCycle: EndLoop self
				)
			)
			(1
				(rabidDog
					view: 291
					setLoop: 1
					setCel: 0
					cycleSpeed: 3
					setCycle: Forward
				)
				(= seconds 3)
			)
			(2
				(rabidDog
					view: 290
					setLoop: 15
					setCel: 0
					cycleSpeed: 18
					setCycle: 0
				)
				(astroChicken die:)
				(self dispose:)
			)
		)
	)
)

(instance sellOutScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 0)
				((= newSq4Actor (Sq4Actor new:))
					view: 271
					posn: 381 25
					setPri: 13
					moveSpeed: 2
					setCel: 0
					setLoop: 0
					init:
					ignoreActors: 1
					setCycle: Forward
					setMotion: MoveTo -1000 25
				)
				(sellOutProp
					view: 271
					posn: 381 25
					setLoop: 1
					init:
					ignoreActors: 1
					setCycle: Forward
					setMotion: MoveTo -1000 25 self
				)
			)
			(1
				(newSq4Actor dispose:)
				(sellOutProp dispose:)
				(= cycles 2)
			)
			(2 (self dispose:))
		)
	)
)

(instance startScript of Script
	(properties)
	
	(method (doit &tmp newEvent temp1)
		(super doit: &rest)
		(if (== state 0)
			(= temp1 ((= newEvent (Event new:)) type?))
			(if (OneOf temp1 1 4) (= seconds 0) (= cycles 1))
			(newEvent dispose:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 4)
				(= messageBits
					(DoDisplay
						{"Flight of the Pullet."}
						#at 10 127
						#color myLowlightColor
						#back myTextColor2
					)
				)
			)
			(1
				(DoDisplay messageBits)
				(curRoom drawPic: 290 style: -32762)
				(astroChicken
					init:
					setCycle: Forward
					observeBlocks:
					livesLeft: 3
					showEggs:
					showPts:
				)
				((astroChicken blocks?) add: theCage)
				(client setScript: 0)
			)
		)
	)
)

(instance killCedricScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= killedCedric TRUE)
				(theSound number: 55 loop: 1 play:)
				(cedric posn: 1000 1000 setMotion: 0)
				(astroChicken view: 272 setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(1
				(cedric
					posn: (astroChicken x?) (astroChicken y?)
					setLoop: 3
					setMotion: MoveTo (astroChicken x?) 200
				)
				(astroChicken
					view: 290
					setLoop: 0
					setCel: 0
					setCycle: Forward
				)
				(backok setCel: 0 init: setCycle: EndLoop)
				(= seconds 2)
			)
			(2
				(= currentPoints (+ currentPoints 25))
				(astroChicken showPts:)
				(backok dispose:)
				(= cycles 2)
			)
			(3 (self dispose:))
		)
	)
)

(instance doHenHouse of Script
	(properties)
	
	(method (doit &tmp newEvent temp1)
		(super doit: &rest)
		(if (== state 5)
			(= temp1 ((= newEvent (Event new:)) type?))
			(if (OneOf temp1 1 4) (= seconds 0) (= cycles 1))
			(newEvent dispose:)
		)
	)
	
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				(= currentPoints (+ currentPoints (* (astroChicken eggsLeft?) 5)))
				(if pointsBits (DoDisplay pointsBits))
				(= pointsBits
					(DoDisplay
						(Format @temp0 {%d} currentPoints)
						#at 275 178
						#color myTextColor6
						#back 25
					)
				)
				(astroChicken2 setMotion: MoveTo 100 100 self)
			)
			(1
				(astroChicken2 setMotion: MoveTo 104 129 self)
			)
			(2
				(astroChicken2 setPri: 11 setMotion: MoveTo 174 129 self)
			)
			(3
				(backok init: setCycle: EndLoop)
				(= cycles 20)
			)
			(4
				(backok dispose:)
				(= cycles 2)
			)
			(5
				(= messageBits
					(DoDisplay
						{Congratulations, in achieving the coveted rank of "Corn-Wheezer," you have won the Pullet Surprise!}
						#at 0 20
						#color myLowlightColor
						#back myTextColor10
						#mode teJustCenter
						#width 319
					)
				)
				(= highScore
					(DoDisplay
						(Format
							@temp0
							{5 X %d eggs = %d bonus points\n\nMega-Hi Score: %d}
							(astroChicken eggsLeft?)
							(* 5 (astroChicken eggsLeft?))
							currentPoints
						)
						#at 0 65
						#color myLowlightColor
						#back myTextColor6
						#mode teJustCenter
					)
				)
			)
			(6
				(DoDisplay messageBits)
				(DoDisplay highScore)
				(curRoom newRoom: 376)
			)
		)
	)
)
