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
	chickenY
	oldChickenX
	oldChickenY
	addY
	local5
	currentFeed
	addX
	local8
	chickenLoop
	chickenDir
	currentLives
	currentLevel
	local13
	printObj
	local15
	gameStarted
	soundIsOn
	wonMsg
)
(procedure (StartNewChicken)
	(= oldChickenX (Random 70 248))
)

(procedure (InsertCoin)
	(if (ego has: iBuckazoids)
		(if (not (-- buckazoids))
			(ego put: iBuckazoids -1)
		)
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
		(StartNewChicken)
		(= oldChickenY 22)
		(= chickenX oldChickenX)
		(= chickenY oldChickenY)
		(= addY 1)
		(= local5 0)
		(= addX 0)
		(= local8 0)
		(= currentFeed 100)
		(= chickenDir 2)
		(= currentLives 3)
		(= currentLevel 0)
		(= local13 0)
		(++ astroChickenPlays)
		(= gameStarted 1)
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
;;;		(TheMenuBar draw:)
;;;		(StatusLine enable:)
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
				(switch chickenDir
					(2 (Chicken loop: 3))
					(0 (Chicken loop: 4))
					(1 (Chicken loop: 5))
				)
			)
			(if (== (Chicken onControl: origin) cYELLOW)
				(= local15 1)
				(self setScript: blowUp)
			)
			(if (== (Chicken onControl: origin) 2048)
				(self setScript: tooHigh)
			)
			(if (and (== (Chicken onControl: origin) 4096) (== local13 0))
				(if (< (Abs addY) 6)
					(self setScript: landedOK)
				else
					(Chicken loop: 8 cel: 0)
					(= local5 0)
					(= local13 1)
					(= addY (- addY (* addY 2)))
					(if (== chickenLoop 0)
						(= chickenLoop 4)
						(= chickenDir 0)
						(= addX 4)
						(Chicken setCycle: Forward)
					else
						(= chickenLoop 5)
						(= chickenDir 1)
						(= addX -4)
						(Chicken setCycle: Reverse)
					)
					(= local8 -10)
				)
			)
			(cond 
				((== local5 1)
					(if (< (-- addY) -10)
						(= addY -10))
						(-- currentFeed)
					)
				((> (++ addY) 10)
					(= addY 10)
				)
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
					((== local8 12)
						(= local8 0)
						(= addX 0)
					)
					((!= (Abs addX) 4)
						(if (== chickenLoop 1)
							(= addX -2)
						else
							(= addX 2)
						)
					)
				)
			)
			(if (< chickenX 52)
				(= chickenX 265)
			)
			(if (> chickenX 265)
				(= chickenX 52)
			)
			(Chicken
				x: (+= chickenX addX)
				y: (+= chickenY addY)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(switch (event message?)
					(`+
						(if (> speed 1)
							(theGame setSpeed: (-- speed))
						)
					)
					(`-
						(if (< speed 16)
							(theGame setSpeed: (++ speed))
						)
					)
					(`=
						(theGame setSpeed: 5)
					)
					(`#2
						(if soundIsOn
							(= soundIsOn FALSE)
							(DoSound SoundOn FALSE)
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
						(= inCartoon FALSE)
						(curRoom newRoom: 29)
					)
				)
			)
			(saidEvent
				(cond 
					((Said 'play[/game,astro,astro]')
						(Print 290 1)
					)
					((Said 'aid[<get]')
						(Print 290 2)
					)
					((Said 'read,look,use/instruction')
						(Print 290 3)
					)
					((Said 'insert,use,drop[<in]/bill')
						(InsertCoin)
					)
					((or (Said 'disembark,quit[/game,device]') (Said '/bye'))
						(= saveDisabled FALSE)
						(= inCartoon FALSE)
						(curRoom newRoom: 29)
;;;						(= quit TRUE)
					)
					((Said 'beat,tilt/game,device')
						(Print 290 4)
					)
					((Said 'look[/area,around,game,device]')
						(Print 290 5)
					)
					((Said 'look/letter')
						(if wonMsg
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
					((or (Said 'use/decoder,relic') (Said 'decode/letter'))
						(= inCartoon TRUE)
						(RedrawCast)
						(if (ego has: iDecoderRing)
							(if (and (< astroChickenScore 120) (not decodedMessage) wonMsg)
								(theGame changeScore: 20)
								(+= astroChickenScore 20)
								(= decodedMessage TRUE)
							)
							(= printObj
								(Print 290 10
									#font 603
									#icon 242 0 5
									#width 240
									#at -1 143
								)
							)
							(= wonMsg 0)
							(= saveDisabled FALSE)
							(= inCartoon FALSE)
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
							(= addX 0)
							(if (== local5 1)
								(= local5 0)
								(switch chickenDir
									(2 (Chicken loop: 3))
									(0 (Chicken loop: 4))
									(1 (Chicken loop: 5))
								)
							else
								(= local5 1)
								(switch chickenDir
									(2 (Chicken loop: 2))
									(0 (Chicken loop: 0))
									(1 (Chicken loop: 1))
								)
							)
						)
					)
					(dirS
						(if (== local15 0)
							(= chickenDir 2)
							(Chicken loop: (if (== local5 1) 2 else 3) cel: 0)
						)
					)
					(dirE
						(if (== local15 0)
							(= chickenDir 0)
							(Chicken loop: (if (== local5 1) 0 else 4) cel: 0)
						)
					)
					(dirW
						(if (== local15 0)
							(= chickenDir 1)
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
							(= chickenDir 2)
							(Chicken loop: (if (== local5 1) 2 else 3) cel: 0)
						)
					)
				)
			)
			(joyDown
				(if (== local15 0)
					(Chicken setCycle: Forward)
					(= addX 0)
					(= local5 1)
					(switch chickenDir
						(2 (Chicken loop: 2))
						(0 (Chicken loop: 0))
						(1 (Chicken loop: 1))
					)
				)
			)
			(joyUp
				(if (== local15 0)
					(Chicken setCycle: Forward)
					(= addX 0)
					(= local5 0)
					(switch chickenDir
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
					(= inCartoon FALSE)
					(curRoom newRoom: 29)
				)
				(if
					(and
						(<= 39 (event x?))
						(<= (event x?) 142)
						(<= 175 (event y?))
						(<= (event y?) 189)
						(not gameStarted)
					)
					(event claimed: TRUE)
					(InsertCoin)
				)
			)
		)
	)
)

(instance intro of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(= inCartoon FALSE)
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
				(= gameStarted FALSE)
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
			(3
				(self changeState: 0)
			)
		)
	)
)

(instance landedOK of Script
	(method (changeState newState &tmp [temp0 5])
		(switch (= state newState)
			(0
				(= local15 1)
				(Chicken loop: 6 cel: 0 setCycle: EndLoop self)
			)
			(1
				(Bacock init: setCycle: EndLoop self)
			)
			(2
				(= seconds 2)
			)
			(3
				(Bacock dispose:)
				(RedrawCast)
				(++ currentLevel)
				(if (< astroChickenScore 50)
					(theGame changeScore: 5)
					(+= astroChickenScore 5)
				)
				(if
					(or
						(== currentLevel 3)
						(== currentLevel 6)
						(== currentLevel 9)
					)
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
				(StartNewChicken)
				(= chickenX oldChickenX)
				(= chickenY oldChickenY)
				(= addY 1)
				(= local5 0)
				(= addX 0)
				(= local8 0)
				(= chickenDir 2)
				(= currentFeed 100)
				(Chicken
					x: oldChickenX
					y: oldChickenY
					loop: 3
					setCycle: Forward
				)
				(= local15 0)
				(client setScript: 0)
			)
		)
	)
)

(instance youWon of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Csong stop:)
				(Chicken dispose:)
				(if (< astroChickenScore 100)
					(theGame changeScore: 50)
					(+= astroChickenScore 50)
				)
				(client drawPic: 290)
				(Display 290 12
					p_at 148 180
					p_font 600
					p_width 90
					p_color vBLACK
				)
				(= wonMsg TRUE)
				(= inCartoon TRUE)
				(TheMenuBar draw: state: TRUE)
;;;				(StatusLine enable: state: TRUE)
				(User canInput: TRUE)
				(= printObj
					(Display 290 16
						p_at 60 30
						p_width 200
						p_color vWHITE
						p_back vBLACK
						p_font 603
					)
				)
;;;				(theGame restart:)
			)
		)
	)
)

(instance blowUp of Script
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
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= addY 1)
				(= local5 0)
				(= addX 0)
				(= local8 0)
				(= chickenDir 2)
				(= local15 0)
				(= local13 0)
				(Chicken loop: 3)
				(client setScript: 0)
			)
		)
	)
)

(instance death of Script
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
				(StartNewChicken)
				(Chicken x: oldChickenX y: oldChickenY show:)
				(= chickenX oldChickenX)
				(= chickenY oldChickenY)
				(= addY 1)
				(= local5 0)
				(= addX 0)
				(= local8 0)
				(= chickenDir 2)
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
	(method (init)
		(super init:)
		(self
			view: 290
			ignoreActors:
			setLoop: 3
			setCel: 0
			setPri: 13
			x: chickenX
			y: chickenY
			setStep: 1
		)
	)
)

(instance guysLeft of Prop
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
	(method (init)
		(super init:)
		(self view: 291 setLoop: 3 setPri: 14 posn: 155 91)
	)
)

(instance explosion of Prop
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
