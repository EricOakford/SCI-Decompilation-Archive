;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include sci.sh)
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
	theFPlay
	local1
	local2
	local3
	local4
	[local5 25]
	[local30 25]
	[local55 25]
	[local80 25]
)
(procedure (localproc_039e param1 param2 &tmp temp0 [temp1 4] temp5)
	(= temp5 (if param2 myHighlightColor else global162))
	(switch param1
		(fIntro
			(TextSize @[temp1 0] @local5 61 0 0)
			(= temp0 (- 240 (/ (- [temp1 3] [temp1 1]) 2)))
			(Display
				@local5
				dsCOORD
				temp0
				103
				dsFONT
				61
				dsCOLOR
				global151
			)
			(Display
				@local5
				dsCOORD
				temp0
				103
				dsFONT
				60
				dsCOLOR
				temp5
			)
		)
		(fPlay
			(TextSize @[temp1 0] @local30 61 0 0)
			(= temp0 (- 240 (/ (- [temp1 3] [temp1 1]) 2)))
			(Display
				@local30
				dsCOORD
				temp0
				113
				dsFONT
				61
				dsCOLOR
				global151
			)
			(Display
				@local30
				dsCOORD
				temp0
				113
				dsFONT
				60
				dsCOLOR
				temp5
			)
		)
		(fContinue
			(TextSize @[temp1 0] @local55 61 0 0)
			(= temp0 (- 240 (/ (- [temp1 3] [temp1 1]) 2)))
			(Display
				@local55
				dsCOORD
				temp0
				123
				dsFONT
				61
				dsCOLOR
				global151
			)
			(Display
				@local55
				dsCOORD
				temp0
				123
				dsFONT
				60
				dsCOLOR
				temp5
			)
		)
		(fQuit
			(TextSize @[temp1 0] @local80 61 0 0)
			(= temp0 (- 240 (/ (- [temp1 3] [temp1 1]) 2)))
			(Display
				@local80
				dsCOORD
				temp0
				133
				dsFONT
				61
				dsCOLOR
				global151
			)
			(Display
				@local80
				dsCOORD
				temp0
				133
				dsFONT
				60
				dsCOLOR
				temp5
			)
		)
	)
)

(instance rm100 of LBRoom
	(properties
		picture 100
	)
	
	(method (init)
		(if (DoSound sndGET_AUDIO_CAPABILITY)
			(= msgType 2)
		else
			(= msgType 1)
		)
		(LoadMany 128 108 151 101)
		(LoadMany 132 100 20 23)
		(LoadMany 130 964)
		(self setRegions: 92)
		(Palette palSET_INTENSITY 0 255 0)
		(super init:)
		(Message msgGET 100 1 0 0 1 @local5)
		(Message msgGET 100 2 0 0 1 @local30)
		(Message msgGET 100 4 0 0 1 @local80)
		(Message msgGET 100 3 0 0 1 @local55)
		(theIconBar disable:)
		(= local1 (Graph grSAVE_BOX 99 185 142 319 1))
		(= local2 (Graph grSAVE_BOX 123 151 133 185 1))
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
	
	(method (doit &tmp temp0)
		(super doit:)
		(if script
		else
			(= temp0
				(features firstTrue: #onMe mouseX (- mouseY 10))
			)
			(if (and (IsObject temp0) (!= temp0 theFPlay))
				(localproc_039e theFPlay 0)
				(localproc_039e temp0 1)
				(= theFPlay temp0)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0 temp1 temp2)
		(= temp1 (event type?))
		(= temp0 (event message?))
		(cond 
			(
				(or
					(and (== temp1 1) (not (event modifiers?)))
					(and (== temp1 4) (== temp0 13))
				)
				(event claimed: 1)
				(switch theFPlay
					(fIntro
						(Graph grRESTORE_BOX local1)
						(Graph grRESTORE_BOX local2)
						(Graph grUPDATE_BOX 99 185 142 319 1)
						(Graph grUPDATE_BOX 123 151 133 185 1)
						(if (not (curRoom script?))
							(curRoom setScript: sCartoon)
						)
					)
					(fPlay
						(ego get: -1 2)
						(if (not (curRoom script?))
							(curRoom setScript: sCartoon)
						)
					)
					(fContinue
						(theGame restore:)
						(localproc_039e fIntro 0)
						(localproc_039e fPlay 0)
						(localproc_039e fContinue 0)
						(localproc_039e fQuit 0)
						(mouseDownHandler addToFront: self)
						(keyDownHandler addToFront: self)
						(directionHandler addToFront: self)
					)
					(fQuit (= quit 1))
				)
			)
			((not (& temp1 $0040)))
			((== temp0 1)
				(localproc_039e theFPlay 0)
				(if
				(>= (= temp2 (- (features indexOf: theFPlay) 1)) 0)
					(= theFPlay (features at: temp2))
				else
					(= theFPlay (features at: (- (features size?) 1)))
				)
				(localproc_039e theFPlay 1)
			)
			((== temp0 5)
				(localproc_039e theFPlay 0)
				(if
					(<
						(= temp2 (+ (features indexOf: theFPlay) 1))
						(features size?)
					)
					(= theFPlay (features at: temp2))
				else
					(= theFPlay (features at: 0))
				)
				(localproc_039e theFPlay 1)
			)
		)
	)
	
	(method (newRoom)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(directionHandler delete: self)
		(user canInput: 0 canControl: 0)
		(super newRoom: &rest)
	)
)

(instance sStart of Script
	(properties)
	
	(method (doit)
		(if (< local3 100)
			(Palette palSET_INTENSITY 0 255 (++ local3))
			(if (== local3 100) (self cue:))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				(theGame setCursor: 996)
				(user canInput: 1 canControl: 1)
				(theMusic number: 20 flags: 1 play:)
				(localproc_039e fIntro 0)
				(= ticks 40)
			)
			(2
				(localproc_039e fPlay 0)
				(= ticks 40)
			)
			(3
				(localproc_039e fContinue 0)
				(= ticks 40)
			)
			(4
				(localproc_039e fQuit 0)
				(= ticks 120)
			)
			(5
				(theMusic number: 23 flags: 1 play:)
				(localproc_039e fPlay 1)
				(= theFPlay fPlay)
				(theGame setCursor: 999)
				(self dispose:)
			)
		)
	)
)

(instance sCartoon of Script
	(properties)
	
	(method (doit)
		(if (and local4 local3)
			(Palette palSET_INTENSITY 0 255 (-- local3))
			(if (not local3) (self cue:))
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (== theFPlay fPlay)
					(self changeState: 18)
				else
					(sparkle init:)
					(wake init: setCycle: RTRandCycle)
					(smoke init: setCycle: RTRandCycle)
					(theMusic number: 100 loop: 1 flags: 1 play: self)
					(theGame handsOff: setCursor: 996 1 304 172)
					(= seconds 3)
				)
			)
			(1
				(sparkle
					setLoop: 2
					setCycle: Fwd
					setMotion:
						DPath
						17
						93
						39
						91
						55
						95
						116
						115
						138
						117
						160
						113
						167
						101
						161
						94
						self
				)
			)
			(2
				(sparkle setLoop: 3 setCel: 0 setCycle: End self)
			)
			(3
				(sparkle
					setLoop: 2
					setCel: 0
					posn: 84 27
					setCycle: End self
				)
			)
			(4
				(sparkle
					ignoreControl: -32768
					setLoop: 2
					setCel: 0
					posn: 66 153
					setCycle: End self
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
				(= temp0 (CelWide 151 0 0))
				(creditTitle setMotion: MoveTo (- 0 temp0) 102 self)
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
				(curRoom newRoom: (if (== theFPlay fPlay) 26 else 110))
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
		signal $0800
		moveSpeed 0
	)
)

(instance creditName of Actor
	(properties
		x 164
		y 216
		view 151
		cel 1
		signal $0800
		moveSpeed 0
	)
)

(instance sparkle of Actor
	(properties
		y 100
		view 108
		loop 2
		priority 15
		signal $4010
		cycleSpeed 4
		moveSpeed 4
	)
)
