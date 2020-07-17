;;; Sierra Script 1.0 - (do not remove this comment)
(script# 290)
(include game.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use Block)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	astro 0
)

(local
	numEggs
	ptsBits
	farmerTimer
	local3
	local4
	local5
	local6
	local7
	currentPoints
	messageBits
	highScore
	oldVolume
	eggBits
	scoreBits
	[local14 6] = [0 5 4 3 2 1]
	[local20 6] = [0 9 8 7 6 5]
	local26 =  50
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
		(Load VIEW 290)
		(Load PICTURE 291)
		(LoadMany SOUND 55 140 56 122 131 147 144 146 827)
		(self drawPic: 290 overlay: 291 HSHUTTER setScript: startScript)
		(super init:)
		(= picture 290)
		(= oldVolume (theGame masterVolume:))
		(musicToggle init:)
		(mouseDownHandler add: cast features)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(Palette PALCycle 48 55 1)
		(cond 
			((curRoom script?))
			((not (-- local26))
				(= local26 50)
				(if (not (cast contains: henHouseRight))
					(if (not (cast contains: rock)) (rock init:))
					(if (not (cast contains: grass)) (grass init:))
				)
			)
		)
		(cond 
			((curRoom script?))
			(local3
				(if (and (not (-- local3)) local4)
					((cornStalk new:) init:)
					(= local3 3)
					(-- local4)
				)
			)
			(
				(and
					(>= currentPoints 250)
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
					(= local3 3)
					(= local4 (Random 4 9))
				)
			)
			((<= temp0 80) (weasel init:))
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
		(if ptsBits (DoDisplay ptsBits))
		(if messageBits (DoDisplay messageBits))
		(if highScore (DoDisplay highScore))
		(if eggBits (DoDisplay eggBits))
		(if scoreBits (DoDisplay scoreBits))
		(super newRoom: &rest)
	)
)

(class astroChicken of Actor
	(properties
		view 290
		signal (| fixedLoop fixPriOn)
		livesLeft 3
		eggsLeft 10
		wireLeft 0
	)
	
	(method (init &tmp [str 5])
		(music number: 54 loop: -1 vol: 127 playBed:)
		(super init:)
		(self
			show:
			x: 160
			y: 40
			eggsLeft: 10
			loop: 0
			wireLeft: 0
			showEggs:
			showPts:
		)
		(astroIndicator init: addToPic:)
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
		(if ptsBits (DoDisplay ptsBits))
		(= ptsBits
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
	)
	
	(method (die)
		(if (not (-- livesLeft))
			(Print 290 0)
			(if modelessDialog (modelessDialog dispose:))
			(theGame restart:)
		else
			(winged dispose:)
			(cast eachElementDo: #dispose)
			(curRoom drawPic: 290)
			(self init:)
			(astroIndicator cel: (- (astroIndicator cel?) 1) init:)
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
		(if ptsBits (DoDisplay ptsBits))
		(= ptsBits
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
				(= currentPoints (+ currentPoints [local14 temp41]))
			else
				(= currentPoints (+ currentPoints [local20 temp41]))
			)
		)
		(self showPts:)
	)
)

(class ScrollActor of Actor
	(properties
		view 290
		priority 12
		signal (| fixedLoop fixPriOn)		
		scrollSpeed 9
		deathLoop 0
		deathMusic 0
	)
	
	(method (init)
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
		(if deathLoop
			(music stop:)
			(eggSplatting stop:)
			(theSound number: deathMusic loop: 1 play: self)
			(astroChicken hide:)
			(self
				setLoop: deathLoop
				setCel: 0
				cycleSpeed: 2
				setCycle: EndLoop
			)
		)
	)
	
	(method (doEgg)
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
		(= scrollSpeed 11)
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
		(= scrollSpeed 11)
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
		(= scrollSpeed 13)
		(= loop 4)
		(= y (astroChicken y?))
		(super init:)
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
			((== scrollSpeed 9))
			((astroChicken loop?)
				(astroChicken
					wireLeft: (- (astroChicken wireLeft?) 1)
					incScore: 5
				)
				(weasel scrollSpeed: 9)
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
		(= farmerTimer (Random 5 10))
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
		((cornTop new:) init:)
	)
)

(instance cornTop of ScrollActor
	(properties
		y 120
		loop 11
		cel 2
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
		(cornPicker play:)
		(astroChicken getEgg:)
		(self hide:)
	)
)

(instance rabidDog of ScrollActor
	(properties
		deathLoop 10
		deathMusic 140
	)
	
	(method (init)
		(= loop 15)
		(= cycleSpeed (= cel 0))
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
	
	(method (doChicken)
		(if (and (== loop 15) (not cycler))
			(dogBarking play:)
			(if (< (- y (astroChicken y?)) 50)
				(super doChicken:)
			else
				(self setCycle: EndLoop)
			)
		)
	)
	
	(method (doEgg)
		(astroChicken incScore: 1)
		(eggFall stop:)
		(eggSplatting play:)
		(egg hide: dispose:)
		(self setLoop: 9 setCel: 0 setCycle: EndLoop)
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
		(cornPicker play:)
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

(instance astroIndicator of View
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

(instance egg of Actor
	(properties
		view 290
		priority 13
		signal (| fixedLoop fixPriOn)
	)
	
	(method (init)
		(super init:)
		(eggFall play:)
		(= x (astroChicken x?))
		(= y (+ (astroChicken y?) 5))
		(= local6 7)
		(= local7 1)
		(= local5 2)
		(self setLoop: 2 setCycle: Forward)
	)
	
	(method (doit)
		(super doit:)
		(self posn: (+ x local6) (+ y local7))
		(if (not (-- local5))
			(-- local6)
			(++ local7)
			(if (>= y 150)
				((eggSplat new:) init:)
				(self dispose:)
			else
				(= local5 2)
			)
		)
	)
)

(instance eggSplat of Actor
	(properties
		y 155
		view 290
		loop 3
		priority 12
		signal (| fixedLoop fixPriOn)
		xStep 9
	)
	
	(method (init)
		(super init:)
		(eggFall stop:)
		(eggSplatting play:)
		(= x (egg x?))
		(self setCycle: EndLoop setMotion: MoveTo -5 y self)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance buckShot of Actor
	(properties
		yStep 9
		view 290
		loop 11
		cel 3
		signal (| ignrAct ignrHrz skipCheck fixedLoop)
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(= farmerTimer 0)
		(self
			posn: (crazedFarmer x?) 108
			setMotion: MoveTo (crazedFarmer x?) -18 self
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(astroChicken isNotHidden:)
				(astroChicken onMe: self)
			)
			(music stop:)
			(eggFall stop:)
			(winged play: astroChicken)
			(astroChicken hide: die:)
			(self dispose:)
		)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance henHouseLeft of Actor
	(properties
		x 500
		y 145
		view 290
		loop 11
		cel 8
		priority 3
		signal (| ignrAct skipCheck fixedLoop fixPriOn)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (> x 169) (= x (- x 9)) else (= x 160))
	)
)

(instance henHouseRight of Actor
	(properties
		x 500
		y 145
		view 290
		loop 11
		cel 9
		priority 12
		signal (| ignrAct skipCheck fixedLoop fixPriOn)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (> x 169) (= x (- x 9)) else (= x 160))
	)
)

(instance backok of Prop
	(properties
		x 100
		y 90
		view 290
		loop 14
	)
)

(instance astroChicken2 of Actor
	(properties
		view 290
		signal fixedLoop
	)
)

(instance musicToggle of Feature
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
						(== (event message?) `^s)
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

(instance startScript of Script
	(properties)
	
	(method (doit &tmp newEvent temp1)
		(super doit: &rest)
		(if (< register 40) (++ register) else (self cue:))
		(if (== state 0)
			(= temp1 ((= newEvent (Event new:)) type?))
			(if (OneOf temp1 1 4) (= seconds 0) (= cycles 1))
			(newEvent dispose:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
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
				(curRoom drawPic: 290)
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
				(if ptsBits (DoDisplay ptsBits))
				(= ptsBits
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
				(theGame restart:)
			)
		)
	)
)
