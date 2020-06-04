;;; Sierra Script 1.0 - (do not remove this comment)
(script# 290)
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)
(use Reverse)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	rm290 0
)

(local
	chickenX
	cihckenY
	oldChickenX
	oldChickenY
	local4
	local5
	currentFeed
	local7
	local8
	chickenLoop
	local10
	currentLives
	currentLevel
	local13
	saveBits
	local15
	local16
	soundIsOn
	local18
)
(procedure (localproc_0f30)
	(= oldChickenX (Random 70 248))
)

(procedure (DoDisplay)
	(if (ego has: iBuckazoids)
		(if (not (-- buckazoids)) (ego put: iBuckazoids -1))
		(curRoom drawPic: 292)
		(Display 290 13
			p_at 52 180
			p_width 91
			p_color vBLACK
			p_back vBLACK
			p_font 600
		)
		(Display 290 17
			p_at 50 180
			p_width 55
			p_color vWHITE
			p_back vBLACK
			p_font 600
		)
		(Display 290 12
			p_at 148 180
			p_font 600
			p_width 90
			p_color vBLACK
		)
		(guysLeft init:)
		(localproc_0f30)
		(= oldChickenY 22)
		(= chickenX oldChickenX)
		(= cihckenY oldChickenY)
		(= local4 1)
		(= local5 0)
		(= local7 0)
		(= local8 0)
		(= currentFeed 100)
		(= local10 2)
		(= currentLives 3)
		(= currentLevel 0)
		(= local13 0)
		(++ astroChickenPlays)
		(= local16 1)
		(= local15 0)
		(Chicken init:)
		(Csong play: loop: -1)
		(HandsOff)
		(= soundIsOn (GetMenu soundI p_value))
		(TheMenuBar hide: state: FALSE)
		(StatusLine enable: state: TRUE)
		(curRoom setScript: 0)
	else
		(Print 290 18)
	)
)

(instance rm290 of Room
	(properties
		style IRISOUT
	)
	
	(method (init)
		(Load VIEW 290)
		(Load VIEW 291)
		(Load PICTURE 290)
		(Load PICTURE 291)
		(Load PICTURE 292)
		(Load SOUND 26)
		(Load SOUND 33)
		(super init:)
		(= saveDisabled TRUE)
		(self setScript: intro)
	)
	
	(method (doit &tmp [str 30])
		(super doit:)
		(if (== script 0)
			(Display
				(Format @str 290 0 currentFeed)
				p_at 80 180
				p_width 15
				p_color vWHITE
				p_back vBLACK
				p_font 600
			)
			(if (not currentFeed)
				(HandsOff)
				(= local15 1)
				(= local5 0)
				(switch local10
					(2 (Chicken loop: 3))
					(0 (Chicken loop: 4))
					(1 (Chicken loop: 5))
				)
			)
			(if (== (Chicken onControl: 1) 16384)
				(= local15 1)
				(self setScript: blowUp)
			)
			(if (== (Chicken onControl: 1) 2048)
				(self setScript: tooHigh)
			)
			(if
			(and (== (Chicken onControl: 1) 4096) (== local13 0))
				(if (< (Abs local4) 6)
					(self setScript: landedOK)
				else
					(Chicken loop: 8 cel: 0)
					(= local5 0)
					(= local13 1)
					(= local4 (- local4 (* local4 2)))
					(if (== chickenLoop 0)
						(= chickenLoop 4)
						(= local10 0)
						(= local7 4)
						(Chicken setCycle: Forward)
					else
						(= chickenLoop 5)
						(= local10 1)
						(= local7 -4)
						(Chicken setCycle: Reverse)
					)
					(= local8 -10)
				)
			)
			(cond 
				((== local5 1) (if (< (-- local4) -10) (= local4 -10)) (-- currentFeed))
				((> (++ local4) 10) (= local4 10))
			)
			(if (and (!= (Chicken loop?) 2) (== local5 1))
				(= local8 1)
				(= chickenLoop (Chicken loop?))
			)
			(if (!= local8 0)
				(if
					(or
						(== local5 0)
						(and (== local5 1) (== (Chicken loop?) 2))
					)
					(++ local8)
				)
				(cond 
					((== local8 12) (= local8 0) (= local7 0))
					((!= (Abs local7) 4) (if (== chickenLoop 1) (= local7 -2) else (= local7 2)))
				)
			)
			(if (< chickenX 52) (= chickenX 265))
			(if (> chickenX 265) (= chickenX 52))
			(Chicken
				x: (= chickenX (+ chickenX local7))
				y: (= cihckenY (+ cihckenY local4))
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(switch (event message?)
					(`+
						(if (> speed 1) (theGame setSpeed: (-- speed)))
					)
					(`-
						(if (< speed 16) (theGame setSpeed: (++ speed)))
					)
					(`= (theGame setSpeed: 5))
					(`#2
						(if soundIsOn
							(= soundIsOn FALSE)
							(DoSound  SoundOn FALSE)
							(SetMenu soundI p_value FALSE p_text {Turn on})
						else
							(= soundIsOn TRUE)
							(DoSound SoundOn TRUE)
							(SetMenu soundI p_value TRUE p_text {Turn off})
						)
					)
					(`#6
						(event claimed: TRUE)
						(= saveDisabled FALSE)
						(= global159 0)
						(curRoom newRoom: 29)
					)
				)
			)
			(saidEvent
				(cond 
					((Said 'play[/game,astro,astro]') (Print 290 1))
					((Said 'aid[<get]') (Print 290 2))
					((Said 'read,look,use/instruction') (Print 290 3))
					((Said 'insert,use,drop[<in]/bill') (DoDisplay))
					(
					(or (Said 'disembark,quit[/game,device]') (Said '/bye'))
						(= saveDisabled FALSE)
						(= global159 0)
						(curRoom newRoom: 29)
					)
					((Said 'beat,tilt/game,device') (Print 290 4))
					((Said 'look[/area,around,game,device]') (Print 290 5))
					((Said 'look/letter')
						(if local18
							(Print 290 6)
							(if (ego has: iDecoderRing)
								(Print 290 7)
							else
								(Print 290 8)
								(curRoom setScript: intro)
							)
						else
							(Print 290 9)
						)
					)
					(
					(or (Said 'use/decoder,relic') (Said 'decode/letter'))
						(= global159 1)
						(RedrawCast)
						(if (ego has: iDecoderRing)
							(if
							(and (< astroChickenScore 120) (not decodedMessage) local18)
								(theGame changeScore: 20)
								(= astroChickenScore (+ astroChickenScore 20))
								(= decodedMessage TRUE)
							)
							(= saveBits
								(Print 290 10
									#font 603
									#icon 242 0 5
									#width 240
									#at -1 143
								)
							)
							(= local18 0)
							(= saveDisabled 0)
							(= global159 0)
							(self newRoom: 29)
						else
							(Print 290 11)
							(curRoom setScript: intro)
						)
					)
				)
			)
			(direction
				(= local13 0)
				(switch (event message?)
					(dirN
						(if (== local15 0)
							(Chicken setCycle: Forward)
							(= local7 0)
							(if (== local5 1)
								(= local5 0)
								(switch local10
									(2 (Chicken loop: 3))
									(0 (Chicken loop: 4))
									(1 (Chicken loop: 5))
								)
							else
								(= local5 1)
								(switch local10
									(2 (Chicken loop: 2))
									(0 (Chicken loop: 0))
									(1 (Chicken loop: 1))
								)
							)
						)
					)
					(dirS
						(if (== local15 0)
							(= local10 2)
							(Chicken loop: (if (== local5 1) 2 else 3) cel: 0)
						)
					)
					(dirE
						(if (== local15 0)
							(= local10 0)
							(Chicken loop: (if (== local5 1) 0 else 4) cel: 0)
						)
					)
					(dirW
						(if (== local15 0)
							(= local10 1)
							(Chicken loop: (if (== local5 1) 1 else 5) cel: 0)
						)
					)
					(dirNW
						(event claimed: TRUE)
						(return)
					)
					(dirNE
						(event claimed: TRUE)
						(return)
					)
					(dirSW
						(event claimed: TRUE)
						(return)
					)
					(dirSE
						(event claimed: TRUE)
						(return)
					)
					(dirStop
						(if (== local15 0)
							(= local10 2)
							(Chicken loop: (if (== local5 1) 2 else 3) cel: 0)
						)
					)
				)
			)
			(joyDown
				(if (== local15 0)
					(Chicken setCycle: Forward)
					(= local7 0)
					(= local5 1)
					(switch local10
						(2 (Chicken loop: 2))
						(0 (Chicken loop: 0))
						(1 (Chicken loop: 1))
					)
				)
			)
			(joyUp
				(if (== local15 0)
					(Chicken setCycle: Forward)
					(= local7 0)
					(= local5 0)
					(switch local10
						(2 (Chicken loop: 3))
						(0 (Chicken loop: 4))
						(1 (Chicken loop: 5))
					)
				)
			)
			(mouseDown
				(if
					(and
						(<= 146 (event x?))
						(<= (event x?) 228)
						(<= 178 (event y?))
						(<= (event y?) 187)
					)
					(event claimed: TRUE)
					(= saveDisabled FALSE)
					(= global159 0)
					(curRoom newRoom: 29)
				)
				(if
					(and
						(<= 39 (event x?))
						(<= (event x?) 142)
						(<= 175 (event y?))
						(<= (event y?) 189)
						(not local16)
					)
					(event claimed: TRUE)
					(DoDisplay)
				)
			)
		)
	)
)

(instance intro of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(= global159 0)
				(guysLeft dispose:)
				(client drawPic: 291)
				(Display 290 12
					p_at 148 180
					p_font 600
					p_width 90
					p_color vBLACK
				)
				(Display 290 13
					p_at 52 180
					p_width 91
					p_color vWHITE
					p_back vBLACK
					p_font 600
				)
				(RedrawCast)
				(= local16 0)
				(= seconds 5)
			)
			(1
				(client drawPic: 290)
				(Display 290 12
					p_at 148 180
					p_font 600
					p_width 90
					p_color vBLACK
				)
				(Display 290 13
					p_at 52 180
					p_width 91
					p_color vWHITE
					p_back vBLACK
					p_font 600
				)
				(Display 290 14
					p_at 60 60
					p_width 200
					p_color vWHITE
					p_back vBLACK
					p_font 600
				)
				(= seconds 6)
			)
			(2
				(Display 290 12
					p_at 148 180
					p_font 600
					p_width 90
					p_color vBLACK
				)
				(Display 290 13
					p_at 52 180
					p_width 91
					p_color vWHITE
					p_back vBLACK
					p_font 600
				)
				(Display 290 14
					p_at 60 60
					p_width 200
					p_color vBLACK
					p_back vBLACK
					p_font 600
				)
				(Display 290 15
					p_at 60 30
					p_width 200
					p_color vWHITE
					p_back vBLACK
					p_font 600
				)
				(= seconds 15)
			)
			(3 (self changeState: 0))
		)
	)
)

(instance landedOK of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 5])
		(switch (= state newState)
			(0
				(= local15 1)
				(Chicken loop: 6 cel: 0 setCycle: EndLoop self)
			)
			(1
				(Bacock init: setCycle: EndLoop self)
			)
			(2 (= seconds 2))
			(3
				(Bacock dispose:)
				(RedrawCast)
				(++ currentLevel)
				(if (< astroChickenScore 50)
					(theGame changeScore: 5)
					(= astroChickenScore (+ astroChickenScore 5))
				)
				(if
				(or (== currentLevel 3) (== currentLevel 6) (== currentLevel 9))
					(++ currentLives)
				)
				(if (== currentLevel 10)
					(client setScript: youWon)
					(= local15 1)
				else
					(= cycles 2)
				)
			)
			(4
				(localproc_0f30)
				(= chickenX oldChickenX)
				(= cihckenY oldChickenY)
				(= local4 1)
				(= local5 0)
				(= local7 0)
				(= local8 0)
				(= local10 2)
				(= currentFeed 100)
				(Chicken x: oldChickenX y: oldChickenY loop: 3 setCycle: Forward)
				(= local15 0)
				(client setScript: 0)
			)
		)
	)
)

(instance youWon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Csong stop:)
				(Chicken dispose:)
				(if (< astroChickenScore 100)
					(theGame changeScore: 50)
					(= astroChickenScore (+ astroChickenScore 50))
				)
				(client drawPic: 290)
				(Display 290 12
					p_at 148 180
					p_font 600
					p_width 90
					p_color vBLACK
				)
				(= local18 1)
				(= global159 TRUE)
				(TheMenuBar draw: state: TRUE)
				(User canInput: TRUE)
				(= saveBits
					(Display 290 16
						p_at 60 30
						p_width 200
						p_color vWHITE
						p_back vBLACK
						p_font 603
					)
				)
			)
		)
	)
)

(instance blowUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(boom play:)
				(explosion
					loop: 0
					cel: 0
					x: (Chicken x?)
					y: (+ (Chicken y?) 10)
					init:
					setCycle: EndLoop self
				)
				(Chicken hide:)
			)
			(1
				(explosion loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(2
				(rm290 setScript: death)
				(explosion dispose:)
			)
		)
	)
)

(instance tooHigh of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local4 1)
				(= local5 0)
				(= local7 0)
				(= local8 0)
				(= local10 2)
				(= local15 0)
				(= local13 0)
				(Chicken loop: 3)
				(client setScript: 0)
			)
		)
	)
)

(instance death of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (-- currentLives) 0)
					(client setScript: byeBye)
				else
					(= cycles 2)
				)
			)
			(1
				(localproc_0f30)
				(Chicken x: oldChickenX y: oldChickenY show:)
				(= chickenX oldChickenX)
				(= cihckenY oldChickenY)
				(= local4 1)
				(= local5 0)
				(= local7 0)
				(= local8 0)
				(= local10 2)
				(= currentFeed 100)
				(= local15 0)
				(= local13 0)
				(Chicken loop: 3)
				(client setScript: 0)
			)
		)
	)
)

(instance byeBye of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Csong stop:)
				(gameOver init: setCycle: Forward)
				(TheMenuBar draw: state: TRUE)
				(StatusLine enable: state: TRUE)
				(= seconds 4)
			)
			(1
				(gameOver dispose:)
				(if (== astroChickenPlays 10)
					(curRoom setScript: youWon)
				else
					(Chicken dispose:)
					(curRoom setScript: intro)
				)
			)
		)
	)
)

(instance Chicken of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 290
			ignoreActors:
			setLoop: 3
			setCel: 0
			setPri: 13
			x: chickenX
			y: cihckenY
			setStep: 1
		)
	)
)

(instance guysLeft of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 290
			setLoop: 7
			setCel: 0
			setPri: 15
			x: 100
			y: 189
			stopUpd:
		)
	)
	
	(method (doit)
		(super doit:)
		(self setCel: (- currentLives 1))
	)
)

(instance Bacock of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 291
			setPri: 14
			setLoop: 2
			posn: (- (Chicken x?) 2) (- (Chicken y?) 21)
		)
	)
)

(instance gameOver of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self view: 291 setLoop: 3 setPri: 14 posn: 155 91)
	)
)

(instance explosion of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self view: 291 setPri: 14 setLoop: 0 setCel: 0)
	)
)

(instance Csong of Sound
	(properties
		number 26
		loop -1
	)
)

(instance boom of Sound
	(properties
		number 33
		priority 1
	)
)
