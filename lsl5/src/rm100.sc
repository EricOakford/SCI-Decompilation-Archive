;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include game.sh)
(use Main)
(use LLRoom)
(use Intrface)
(use ForCount)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm100 0
)

(local
	i
	theX
	[typeBuf 50]
	[underbits 12]
	local64
)
(procedure (TypeDisplay &tmp [str 2] keyTyped saveBits)
	(= keyTyped (StrAt @typeBuf i))
	(Format @str {%c} keyTyped)
	(= saveBits
		(Display @str
			p_at theX 160
			p_color myDisplayColor
			p_width 7
			p_mode teJustLeft
			p_font resumeFont
			p_save
		)
	)
	(if (and (< 21 i) (< i 29))
		(= [underbits (- i 22)] saveBits)
	else
		(UnLoad MEMORY saveBits)
	)
	(= theX (+ theX 7))
	(if (== SPACEBAR keyTyped) (= theX (- theX 2)))
)

(instance rm100 of LLRoom
	(properties
		picture 100
		style FADEOUT
	)
	
	(method (init)
		(LoadMany PICTURE 105)
		(LoadMany VIEW 105 106 107 108 109)
		(LoadMany SOUND 101 102 103 104 105)
		(theMusic number: 100 setLoop: -1 flags: mNOPAUSE play:)
		(StrCpy
			@typeBuf
			{Passionate Patti Does PittsbuA Little Undercover Work}
		)
		(super init:)
		(HandsOff)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(SetFFRoom 120)
		(self setScript: sCartoon)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
	)
	
	(method (handleEvent event)
		(if
			(and
				(event type?)
				(!= (event message?) ESC)
				(!= (event message?) `@a)
				(!= (event message?) `@r)
				(!= (event message?) `@t)
			)
			(if
				(Print 100 0
					#title {Fast Forward}
					#icon 990 11 0
					#new
					#button {Really? Show Me!} 1
					#button {Oops} 0
				)
				(theIconBar
					curIcon: (theIconBar at: ICON_SKIP)
					handleEvent: (event type: keyDown message: ESC yourself:)
				)
			)
			(event claimed: TRUE)
		else
			(super handleEvent: event &rest)
		)
	)
)

(instance sCartoon of Script
	
	(method (doit)
		(super doit:)
		(if local64
			(Palette PALCycle 24 31 -1)
			(Palette PALCycle 240 254 -1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 4)
			)
			(1
				(larry init:)
				(patti init:)
				(blankPanel init:)
				(glue init:)
				(curRoom drawPic: 105 10)
				(= seconds 3)
			)
			(2 (larry setCycle: EndLoop self))
			(3
				(broomUp play:)
				(larry setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(4
				(broomUp play:)
				(larry setCel: 0 setCycle: EndLoop self)
			)
			(5
				(glue setPri: 0)
				(larry setLoop: 3 setCycle: EndLoop self)
			)
			(6 (= seconds 3))
			(7
				(brush init:)
				(larry setLoop: 4 setCel: 0 setCycle: EndLoop self)
			)
			(8
				(blankPanel dispose:)
				(larry view: 108 setLoop: 0 setCel: 0 setCycle: EndLoop self)
			)
			(9
				(larry setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(10
				(larry
					setLoop: 1
					setCel: 0
					x: (+ (larry x?) 20)
					setCycle: EndLoop self
				)
			)
			(11
				(larry setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(12
				(= local64 1)
				(= seconds 5)
			)
			(13
				(patti setCycle: Walk setMotion: MoveTo 15 140 self)
			)
			(14
				(patti setMotion: MoveTo 335 140)
				(= i 0)
				(= theX 3)
				(larry setScript: sLarryCartoon)
				(= cycles 1)
			)
			(15
				(TypeDisplay)
				(typewriter play:)
				(if (< (++ i) 29)
					(= cycles (Random 3 5))
					(-- state)
				else
					(= cycles 10)
				)
			)
			(16
				(Display {}
					p_restore [underbits (- (-- i) 22)]
				)
				(backSpace play:)
				(if (> i 22)
					(= cycles 4)
					(-- state)
				else
					(= theX 153)
					(= i 29)
					(= cycles 10)
				)
			)
			(17
				(TypeDisplay)
				(typewriter play:)
				(if (< (++ i) (StrLen @typeBuf))
					(-- state)
					(= cycles (Random 3 5))
				else
					(= cycles 10)
				)
			)
			(18 (= ticks 270))
			(19
				(curRoom newRoom: 110)
				(self dispose:)
			)
		)
	)
)

(instance sLarryCartoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(larry
					setLoop: 4
					setCel: 0
					setCycle: ForwardCounter 4 self
				)
			)
			(1
				(larry
					view: 106
					setLoop: 4
					setCel: 0
					posn: 268 133
					setCycle: CycleTo 3 1 self
					setMotion: MoveTo 263 133 self
				)
			)
			(2)
			(3
				(glue dispose:)
				(larry setCycle: CycleTo 5 1 self)
			)
			(4
				(footInBucket play:)
				(larry setCycle: EndLoop self)
			)
			(5
				(larry
					setLoop: 0
					setCel: 0
					x: (- (larry x?) 5)
					setCycle: CycleTo 4 1 self
				)
			)
			(6
				(glueStretch play:)
				(larry setCycle: EndLoop self)
			)
			(7 (larry setCycle: BegLoop self))
			(8
				(larry
					setLoop: 1
					setCel: 0
					setCycle: ForwardCounter 2 self
				)
			)
			(9
				(larry
					setLoop: 2
					setCel: 0
					setCycle: ForwardCounter 2 self
				)
			)
			(10
				(larry setLoop: 3 setCel: 0 setCycle: EndLoop self)
			)
			(11
				(larry
					view: 107
					setLoop: 0
					setCel: 0
					setCycle: ForwardCounter 2 self
				)
			)
			(12
				(glueStretch play:)
				(larry setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(13 (self dispose:))
		)
	)
)

(instance larry of Actor
	(properties
		x 230
		y 132
		view 105
		loop 1
		signal ignrAct
		cycleSpeed 10
		moveSpeed 10
	)
)

(instance patti of Actor
	(properties
		x -16
		y 140
		view 109
		signal ignrAct
		cycleSpeed 10
		xStep 5
		moveSpeed 10
	)
)

(instance blankPanel of View
	(properties
		x 223
		y 108
		view 108
		loop 3
		signal (| ignrAct stopUpdOn)
	)
)

(instance glue of View
	(properties
		x 251
		y 132
		view 105
		signal (| ignrAct stopUpdOn)
	)
)

(instance brush of View
	(properties
		x 256
		y 132
		view 105
		cel 1
		signal (| ignrAct stopUpdOn)
	)
)

(instance footInBucket of Sound
	(properties
		number 101
	)
)

(instance typewriter of Sound
	(properties
		number 102
	)
)

(instance broomUp of Sound
	(properties
		number 103
	)
)

(instance glueStretch of Sound
	(properties
		number 104
	)
)

(instance backSpace of Sound
	(properties
		number 105
	)
)
