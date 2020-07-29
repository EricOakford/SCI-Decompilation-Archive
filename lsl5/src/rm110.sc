;;; Sierra Script 1.0 - (do not remove this comment)
(script# 110)
(include game.sh)
(use Main)
(use LLRoom)
(use Intrface)
(use LoadMany)
(use Actor)
(use System)

(public
	rm110 0
)

(procedure (CreditDisplay theString theX)
	(Display theString
		p_at (+ theX 1) 43
		p_width 158
		p_mode teJustCenter
		p_color myShadowColor
		p_font giantFont2
	)
	(Display theString
		p_at theX 42
		p_width 158
		p_mode teJustCenter
		p_color 61
		p_font giantFont2
	)
)

(instance rm110 of LLRoom
	(properties
		picture 115
	)
	
	(method (init)
		(larryPic init:)
		(self style: (if (Btst fIsVGA) PIXELDISSOLVE else DISSOLVE))
		(super init:)
		(LoadMany PICTURE 1)
		(LoadMany VIEW 115 116)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(SetFFRoom 120)
		(self setScript: sCartoon)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(theMusic fade: 0 15 12 1)
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
				(Print 110 0
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
	
	(method (changeState newState &tmp [temp0 100])
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(curRoom drawPic: (curRoom picture?))
				(CreditDisplay {Executive Producer\n\nKen Williams} 160)
				(= seconds 4)
			)
			(2
				(curRoom drawPic: (curRoom picture?))
				(CreditDisplay {Creative Director\n\nBill Davis} 160)
				(= seconds 4)
			)
			(3
				(curRoom drawPic: (curRoom picture?))
				(CreditDisplay {Director\n\nAl Lowe} 160)
				(= seconds 4)
			)
			(4
				(curRoom drawPic: (curRoom picture?))
				(CreditDisplay {Producer\n\nGuruka Singh Khalsa} 160)
				(= seconds 4)
			)
			(5
				(curRoom drawPic: (curRoom picture?))
				(larryPic dispose:)
				(pattiPic init:)
				(= ticks 120)
			)
			(6
				(curRoom drawPic: (curRoom picture?))
				(CreditDisplay {Game Designer\n\nAl Lowe} 0)
				(= seconds 4)
			)
			(7
				(curRoom drawPic: (curRoom picture?))
				(CreditDisplay {Production Designer\n\nAndy Hoyos} 0)
				(= seconds 4)
			)
			(8
				(curRoom drawPic: (curRoom picture?))
				(CreditDisplay {Art Designer\n\nJane Cardinal} 0)
				(= seconds 4)
			)
			(9
				(curRoom drawPic: (curRoom picture?))
				(CreditDisplay {Lead Programmer\n\nBrian K. Hughes} 0)
				(= seconds 4)
			)
			(10
				(curRoom drawPic: (curRoom picture?))
				(CreditDisplay {Composer\n\nCraig Safan} 0)
				(= seconds 4)
			)
			(11
				(theMusic fade: 0 15 12 1)
				(curRoom drawPic: 1 IRISIN)
				(= seconds 3)
			)
			(12
				(TimePrint 110 1)
				(theIconBar enable:)
				(curRoom newRoom: 120)
				(self dispose:)
			)
		)
	)
)

(instance pattiPic of View
	(properties
		x 170
		y 177
		view 115
		signal (| fixPriOn stopUpdOn)
	)
)

(instance larryPic of View
	(properties
		x 173
		y 179
		view 116
		signal (| fixPriOn stopUpdOn)
	)
)
