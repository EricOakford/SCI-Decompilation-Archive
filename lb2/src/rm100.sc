;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include game.sh) (include "100.shm")
(use Main)
(use LBRoom)
(use RandCyc)
(use Feature)
(use LoadMany)
(use DPath)
(use Motion)
(use Actor)
(use System)

(public
	rm100 0
)

(local
	highlitOption
	saveBits
	saveBits2
	local3
	local4
	[introBuf 25]
	[playBuf 25]
	[continueBuf 25]
	[quitBuf 25]
)
(procedure (DoDisplay args defColor &tmp theX [len 4] backColor)
	(= backColor (if defColor myHighlightColor else global162))
	(switch args
		(fIntro
			(TextSize @[len 0] @introBuf 61 0 0)
			(= theX (- 240 (/ (- [len 3] [len 1]) 2)))
			(Display @introBuf
				p_at theX 103
				p_font 61
				p_color global151
			)
			(Display @introBuf
				p_at theX 103
				p_font 60
				p_color backColor
			)
		)
		(fPlay
			(TextSize @[len 0] @playBuf 61 0 0)
			(= theX (- 240 (/ (- [len 3] [len 1]) 2)))
			(Display @playBuf
				p_at theX 113
				p_font 61
				p_color global151
			)
			(Display @playBuf
				p_at theX 113
				p_font 60
				p_color backColor
			)
		)
		(fContinue
			(TextSize @[len 0] @continueBuf 61 0 0)
			(= theX (- 240 (/ (- [len 3] [len 1]) 2)))
			(Display @continueBuf
				p_at theX 123
				p_font 61
				p_color global151
			)
			(Display @continueBuf
				p_at theX 123
				p_font 60
				p_color backColor
			)
		)
		(fQuit
			(TextSize @[len 0] @quitBuf 61 0 0)
			(= theX (- 240 (/ (- [len 3] [len 1]) 2)))
			(Display @quitBuf
				p_at theX 133
				p_font 61
				p_color global151
			)
			(Display @quitBuf
				p_at theX 133
				p_font 60
				p_color backColor
			)
		)
	)
)

(instance rm100 of LBRoom
	(properties
		picture 100
	)
	
	(method (init)
		(if (DoSound NumDACs)
			(= msgType CD_MSG)
		else
			(= msgType TEXT_MSG)
		)
		(LoadMany RES_VIEW 108 151 101)
		(LoadMany RES_SOUND 100 20 23)
		(LoadMany RES_SCRIPT DPATH)
		(self setRegions: 92)
		(Palette PALIntensity 0 255 0)
		(super init:)
		(Message MsgGet 100 N_INTRO NULL NULL 1 @introBuf)
		(Message MsgGet 100 N_PLAY NULL NULL 1 @playBuf)
		(Message MsgGet 100 N_QUIT NULL NULL 1 @quitBuf)
		(Message MsgGet 100 N_CONTINUE NULL NULL 1 @continueBuf)
		(theIconBar disable:)
		(= saveBits (Graph GSaveBits 99 185 142 319 1))
		(= saveBits2 (Graph GSaveBits 123 151 133 185 1))
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(directionHandler addToFront: self)
		(lauraBowTitle init:)
		(fIntro init:)
		(fPlay init:)
		(fContinue init:)
		(fQuit init:)
		(self setScript: sStart)
	)
	
	(method (doit &tmp obj)
		(super doit:)
		(if script
		else
			(= obj
				(features firstTrue: #onMe mouseX (- mouseY 10))
			)
			(if (and (IsObject obj) (!= obj highlitOption))
				(DoDisplay highlitOption 0)
				(DoDisplay obj 1)
				(= highlitOption obj)
			)
		)
	)
	
	(method (handleEvent event &tmp eMsg eType temp2)
		(= eType (event type?))
		(= eMsg (event message?))
		(cond 
			(
				(or
					(and (== eType mouseDown) (not (event modifiers?)))
					(and (== eType keyDown) (== eMsg ENTER))
				)
				(event claimed: TRUE)
				(switch highlitOption
					(fIntro
						(Graph GRestoreBits saveBits)
						(Graph GRestoreBits saveBits2)
						(Graph GShowBits 99 185 142 319 1)
						(Graph GShowBits 123 151 133 185 1)
						(if (not (curRoom script?))
							(curRoom setScript: sCartoon)
						)
					)
					(fPlay
						(ego get: -1 iNotebook)
						(if (not (curRoom script?))
							(curRoom setScript: sCartoon)
						)
					)
					(fContinue
						(theGame restore:)
						(DoDisplay fIntro 0)
						(DoDisplay fPlay 0)
						(DoDisplay fContinue 0)
						(DoDisplay fQuit 0)
						(mouseDownHandler addToFront: self)
						(keyDownHandler addToFront: self)
						(directionHandler addToFront: self)
					)
					(fQuit
						(= quit TRUE)
					)
				)
			)
			((not (& eType direction)))
			((== eMsg mouseDown)
				(DoDisplay highlitOption 0)
				(if (>= (= temp2 (- (features indexOf: highlitOption) 1)) 0)
					(= highlitOption (features at: temp2))
				else
					(= highlitOption (features at: (- (features size?) 1)))
				)
				(DoDisplay highlitOption 1)
			)
			((== eMsg 5)
				(DoDisplay highlitOption 0)
				(if
					(<
						(= temp2 (+ (features indexOf: highlitOption) 1))
						(features size?)
					)
					(= highlitOption (features at: temp2))
				else
					(= highlitOption (features at: 0))
				)
				(DoDisplay highlitOption 1)
			)
		)
	)
	
	(method (newRoom)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(directionHandler delete: self)
		(user canInput: FALSE canControl: FALSE)
		(super newRoom: &rest)
	)
)

(instance sStart of Script
	
	(method (doit)
		(if (< local3 100)
			(Palette PALIntensity 0 255 (++ local3))
			(if (== local3 100)
				(self cue:)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				(theGame setCursor: INVIS_CURSOR)
				(user canInput: TRUE canControl: TRUE)
				(theMusic number: 20 flags: 1 play:)
				(DoDisplay fIntro 0)
				(= ticks 40)
			)
			(2
				(DoDisplay fPlay 0)
				(= ticks 40)
			)
			(3
				(DoDisplay fContinue 0)
				(= ticks 40)
			)
			(4
				(DoDisplay fQuit 0)
				(= ticks 120)
			)
			(5
				(theMusic number: 23 flags: mNOPAUSE play:)
				(DoDisplay fPlay 1)
				(= highlitOption fPlay)
				(theGame setCursor: ARROW_CURSOR)
				(self dispose:)
			)
		)
	)
)

(instance sCartoon of Script
	
	(method (doit)
		(if (and local4 local3)
			(Palette PALIntensity 0 255 (-- local3))
			(if (not local3)
				(self cue:)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp theWidth)
		(switch (= state newState)
			(0
				(if (== highlitOption fPlay)
					(self changeState: 18)
				else
					(sparkle init:)
					(wake init: setCycle: RTRandCycle)
					(smoke init: setCycle: RTRandCycle)
					(theMusic number: 100 loop: 1 flags: mNOPAUSE play: self)
					(theGame handsOff: setCursor: INVIS_CURSOR TRUE 304 172)
					(= seconds 3)
				)
			)
			(1
				(sparkle
					setLoop: 2
					setCycle: Forward
					setMotion: DPath
						17 93
						39 91
						55 95
						116 115
						138 117
						160 113
						167 101
						161 94
						self
				)
			)
			(2
				(sparkle setLoop: 3 setCel: 0 setCycle: EndLoop self)
			)
			(3
				(sparkle
					setLoop: 2
					setCel: 0
					posn: 84 27
					setCycle: EndLoop self
				)
			)
			(4
				(sparkle
					ignoreControl: cWHITE
					setLoop: 2
					setCel: 0
					posn: 66 153
					setCycle: EndLoop self
				)
			)
			(5 (= seconds 4))
			(6
				(lauraBowTitle dispose:)
				(sparkle dispose:)
				(creditTitle init: setMotion: MoveTo 12 102 self)
				(creditName init: setMotion: MoveTo 164 128 self)
			)
			(7 0)
			(8 (= seconds 4))
			(9
				(= theWidth (CelWide 151 0 0))
				(creditTitle setMotion: MoveTo (- 0 theWidth) 102 self)
				(creditName setMotion: MoveTo 398 128 self)
			)
			(10 0)
			(11 (= seconds 3))
			(12
				(creditTitle init: loop: 1 setMotion: MoveTo 12 102 self)
				(creditName init: loop: 1 setMotion: MoveTo 164 128 self)
			)
			(13 0)
			(14 (= seconds 4))
			(15
				(creditTitle setMotion: MoveTo 12 210 self)
				(creditName setMotion: MoveTo 164 236 self)
			)
			(16 0)
			(17 0)
			(18 (= local4 1))
			(19
				(curRoom newRoom: (if (== highlitOption fPlay) 26 else 110))
			)
		)
	)
)

(instance fIntro of Feature
	(properties
		x 263
		y 104
		nsTop 103
		nsLeft 208
		nsBottom 113
		nsRight 318
	)
)

(instance fPlay of Feature
	(properties
		x 263
		y 104
		nsTop 113
		nsLeft 208
		nsBottom 123
		nsRight 318
	)
)

(instance fContinue of Feature
	(properties
		x 263
		y 104
		nsTop 123
		nsLeft 208
		nsBottom 133
		nsRight 318
	)
)

(instance fQuit of Feature
	(properties
		x 263
		y 104
		nsTop 133
		nsLeft 208
		nsBottom 143
		nsRight 318
	)
)

(instance lauraBowTitle of View
	(properties
		y 156
		view 101
	)
)

(instance smoke of Prop
	(properties
		x 204
		y 59
		view 101
		loop 1
		cycleSpeed 20
	)
)

(instance wake of Prop
	(properties
		x 230
		y 91
		view 101
		loop 2
		cycleSpeed 20
	)
)

(instance creditTitle of Actor
	(properties
		x 12
		y 190
		view 151
		signal fixedLoop
		moveSpeed 0
	)
)

(instance creditName of Actor
	(properties
		x 164
		y 216
		view 151
		cel 1
		signal fixedLoop
		moveSpeed 0
	)
)

(instance sparkle of Actor
	(properties
		y 100
		view 108
		loop 2
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 4
		moveSpeed 4
	)
)
