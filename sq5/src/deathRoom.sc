;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEATHROOM)
(include game.sh) (include "20.shm")
(use Main)
(use Osc)
(use ForCount)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	deathRoom 0
)

(local
	[str 200]
	[theX 28] = [58 73 87 103 128 142 136 130 128 132 139 147 160 173 184 191 193 189 189 185 176 165 154 144 121 97 105 120]
	[theY 28] = [90 100 104 106 104 101 106 110 112 116 121 127 129 131 129 126 124 125 129 133 138 145 153 161 168 170 176 185]
	[theCel 28] = [0 1 2 3 4 5 6 7 8 9 10 11 12 0 1 2 3 3 4 5 6 2 3 4 3 1 0 1]
	i
	local285
	[deathScript 52] = [8 7 7 7 3 3 2 2 3 1 3 2 2 3 3 3 7 2 2 4 2 2 2 2 1 6 6 0 3 1 1 8 1 1 5 2 7 8 8 2 1 6 7 7 7 8 8 8 7 1 3 7]
)

(enum	;death animations
	rogBeatUp
	rogPuked
	rogBurned
	headExplodes
	accordion
	flattened
	fly
	generic
	pixelOut
)

(procedure (ShowButtons)
	(iWannaQuit init:)
	(iWannaRestore init:)
	(iWannaRestart init:)
	(user canControl: TRUE canInput: TRUE)
	(theIconBar enable:)
	(theIconBar enable: ICON_DO)
	(theIconBar select: (theIconBar at: ICON_DO))
	(theGame setCursor: ARROW_CURSOR)
)

(instance deathRoom of Room
	(properties
		picture 200
	)
	
	(method (init)
		(Palette PALIntensity 0 255 100)
		(PalVary PALVARYKILL)
		(theGame handsOff:)
		(super init:)
		(theMusic1 number: 45 loop: -1 play:)
		(theMusic2 stop:)
		(switch [deathScript deathReason]
			(rogBeatUp
				(curRoom setScript: sRogBeatUp)
			)
			(rogPuked
				(curRoom setScript: sRogPuked)
			)
			(rogBurned
				(curRoom setScript: sRogBurned)
			)
			(headExplodes
				(curRoom setScript: sHeadExplodes)
			)
			(accordion
				(curRoom setScript: sAccordion)
			)
			(flattened
				(curRoom setScript: sFlattened)
			)
			(fly
				(curRoom setScript: sFly)
			)
			(generic
				(curRoom setScript: sGeneric)
			)
			(pixelOut
				(curRoom setScript: sPixelOut)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sRogBeatUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (< deathReason 36)
					(Message MsgGet DEATHROOM N_DEATH_2 NULL NULL deathReason @str)
				else
					(Message MsgGet DEATHROOM N_DEATH_1 NULL NULL (- deathReason 35) @str)
				)
				(Display @str
					p_at 143 68
					p_color 0
					p_back 5
					p_font 1605
					p_width 140
					p_mode teJustCenter
				)
				(rogPart1 view: 2000 loop: 0 cel: 0 x: 24 y: 79 init:)
				(rogPart2
					view: 2000
					loop: 1
					cel: 0
					x: 67
					y: 80
					setPri: 14
					init:
					setCycle: ForwardCounter 2 self
				)
			)
			(1
				(rogPart2 loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(2
				(rogPart2
					setLoop: -1
					setLoop: 2
					cel: (rogPart2 lastCel:)
					setStep: 2 10
					moveSpeed: 0
					setCycle: 0
					setMotion: MoveTo 67 200 self
				)
			)
			(3
				(rogPart2 hide:)
				(ShowButtons)
				(self dispose:)
			)
		)
	)
)

(instance sRogPuked of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (< deathReason 36)
					(Message MsgGet DEATHROOM N_DEATH_2 NULL NULL deathReason @str)
				else
					(Message MsgGet DEATHROOM N_DEATH_1 NULL NULL (- deathReason 35) @str)
				)
				(Display @str
					p_at 143 68
					p_color 0
					p_back 5
					p_font 1605
					p_width 140
					p_mode teJustCenter
				)
				(rogPart1 view: 2001 loop: 0 cel: 0 x: 24 y: 79 init:)
				(rogPart2
					view: 2001
					loop: 1
					cel: 0
					x: 67
					y: 80
					setPri: 14
					init:
					setCycle: EndLoop self
				)
				(rogPart3
					view: 2001
					loop: 2
					cel: 0
					x: 44
					y: 128
					setPri: 14
					init:
					setCycle: EndLoop self
				)
			)
			(1
				(ShowButtons)
				(self dispose:)
			)
		)
	)
)

(instance sRogBurned of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (< deathReason 36)
					(Message MsgGet DEATHROOM N_DEATH_2 NULL NULL deathReason @str)
				else
					(Message MsgGet DEATHROOM N_DEATH_1 NULL NULL (- deathReason 35) @str)
				)
				(Display @str
					p_at 143 68
					p_color 0
					p_back 5
					p_font 1605
					p_width 140
					p_mode teJustCenter
				)
				(rogPart1 view: 2002 loop: 0 cel: 0 x: 24 y: 79 init:)
				(= seconds 2)
			)
			(1
				(Palette PALLoad 200 2)
				(= ticks 15)
			)
			(2
				(rogPart1 view: 2002 loop: 0 cel: 1 x: 40 y: 73)
				(= ticks 15)
			)
			(3
				(Palette PALLoad 202 2)
				(= i 0)
				(= ticks 30)
			)
			(4
				(rogPart2
					view: 2002
					loop: 1
					cel: [theCel i]
					x: [theX i]
					y: [theY i]
					setPri: 14
					init:
				)
				(= ticks 5)
			)
			(5
				(rogPart2
					cel: [theCel i]
					x:
						[theX (if (and (< 12 (++ i)) (< i 21))
							(rogPart2 loop: 2)
						else
							(rogPart2 loop: 1)
						)]
					y: [theY i]
				)
				(if (< i 27) (-- state))
				(= ticks 5)
			)
			(6
				(rogPart2 hide:)
				(ShowButtons)
				(self dispose:)
			)
		)
	)
)

(instance sHeadExplodes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (< deathReason 36)
					(Message MsgGet DEATHROOM N_DEATH_2 NULL NULL deathReason @str)
				else
					(Message MsgGet DEATHROOM N_DEATH_1 NULL NULL (- deathReason 35) @str)
				)
				(Display @str
					p_at 143 68
					p_color 0
					p_back 5
					p_font 1605
					p_width 140
					p_mode teJustCenter
				)
				(rogPart1 view: 2003 loop: 0 cel: 0 x: 24 y: 79 init:)
				(rogPart2
					view: 2003
					loop: 1
					cel: 0
					x: 67
					y: 80
					init:
					setPri: 14
					setCycle: EndLoop self
				)
			)
			(1
				(rogPart2 loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(2
				(if (== deathReason deathNOMASK)
					(ShowButtons)
					(self dispose:)
				else
					(rogPart2 loop: 3 cel: 0)
					(= seconds 2)
				)
			)
			(3
				(rogPart2 dispose:)
				(ShowButtons)
				(self dispose:)
			)
		)
	)
)

(instance sAccordion of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (< deathReason 36)
					(Message MsgGet DEATHROOM N_DEATH_2 NULL NULL deathReason @str)
				else
					(Message MsgGet DEATHROOM N_DEATH_1 NULL NULL (- deathReason 35) @str)
				)
				(Display @str
					p_at 143 68
					p_color 0
					p_back 5
					p_font 1605
					p_width 140
					p_mode teJustCenter
				)
				(rogPart1
					view: 2004
					loop: 0
					cel: 0
					x: 24
					y: 79
					init:
					setCycle: Oscillate 3 self
				)
			)
			(1
				(rogPart1 setCycle: EndLoop self)
			)
			(2
				(ShowButtons)
				(self dispose:)
			)
		)
	)
)

(instance sFlattened of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (< deathReason 36)
					(Message MsgGet DEATHROOM N_DEATH_2 NULL NULL deathReason @str)
				else
					(Message MsgGet DEATHROOM N_DEATH_1 NULL NULL (- deathReason 35) @str)
				)
				(Display @str
					p_at 143 68
					p_color 0
					p_back 5
					p_font 1605
					p_width 140
					p_mode teJustCenter
				)
				(rogPart1 view: 2005 loop: 0 cel: 0 x: 24 y: 79 init:)
				(rogPart2
					view: 2005
					loop: 1
					cel: 0
					x: 67
					y: 80
					setPri: 14
					init:
					setCycle: EndLoop self
				)
			)
			(1 (= seconds 2))
			(2
				(ShowButtons)
				(self dispose:)
			)
		)
	)
)

(instance sFly of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (< deathReason 36)
					(Message MsgGet DEATHROOM N_DEATH_2 NULL NULL deathReason @str)
				else
					(Message MsgGet DEATHROOM N_DEATH_1 NULL NULL (- deathReason 35) @str)
				)
				(Display @str
					p_at 160 68
					p_color 0
					p_back 5
					p_font 1605
					p_width 120
					p_mode teJustCenter
				)
				(rogPart1 view: 2006 loop: 0 cel: 0 x: 20 y: 63 init:)
				(= seconds 2)
			)
			(1
				(ShowButtons)
				(self dispose:)
			)
		)
	)
)

(instance sGeneric of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (< deathReason 36)
					(Message MsgGet DEATHROOM N_DEATH_2 NULL NULL deathReason @str)
				else
					(Message MsgGet DEATHROOM N_DEATH_1 NULL NULL (- deathReason 35) @str)
				)
				(Display @str
					p_at 143 68
					p_color 0
					p_back 5
					p_font 1605
					p_width 140
					p_mode teJustCenter
				)
				(rogPart1 view: 2001 loop: 0 cel: 0 x: 24 y: 79 init:)
				(rogPart2
					view: 2001
					loop: 1
					cel: 0
					x: 67
					y: 80
					setPri: 14
					init:
				)
				(rogPart3
					view: 2001
					loop: 2
					cel: 0
					x: 44
					y: 128
					setPri: 14
					init:
				)
				(= seconds 2)
			)
			(1
				(ShowButtons)
				(self dispose:)
			)
		)
	)
)

(instance sPixelOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(rogPart1
					view: 2001
					loop: 0
					cel: 0
					x: 24
					y: 79
					init:
					addToPic:
				)
				(rogPart2
					view: 2001
					loop: 1
					cel: 0
					x: 67
					y: 80
					setPri: 14
					init:
					addToPic:
				)
				(rogPart3
					view: 2001
					loop: 2
					cel: 0
					x: 44
					y: 128
					setPri: 14
					init:
					addToPic:
				)
				(= seconds 2)
			)
			(1
				(DrawPic 200 PIXELDISSOLVE)
				(= seconds 2)
			)
			(2
				(if (< deathReason 36)
					(Message MsgGet DEATHROOM N_DEATH_2 NULL NULL deathReason @str)
				else
					(Message MsgGet DEATHROOM N_DEATH_1 NULL NULL (- deathReason 35) @str)
				)
				(Display @str
					p_at 143 68
					p_color 0
					p_back 5
					p_font 1605
					p_width 140
					p_mode teJustCenter
				)
				(ShowButtons)
				(self dispose:)
			)
		)
	)
)

(instance rogPart1 of Prop
	(properties
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rogPart2 of Actor
	(properties
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rogPart3 of Prop
	(properties
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance iWannaRestart of View
	(properties
		x 50
		y 170
		view 2099
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(self cel: 1)
				(theGame restart:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance iWannaRestore of View
	(properties
		x 150
		y 170
		view 2099
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(self cel: 1)
				(theGame restore:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance iWannaQuit of View
	(properties
		x 250
		y 170
		view 2099
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(self cel: 1)
				(= quit 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
