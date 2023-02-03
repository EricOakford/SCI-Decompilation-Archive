;;; Sierra Script 1.0 - (do not remove this comment)
(script# 228)
(include sci.sh)
(use Main)
(use eureka)
(use Intrface)
(use Talker)
(use Feature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm228 0
	rogTalker 15
)

(local
	theFuse
	local1
	local2
)
(procedure (localproc_0184 param1 &tmp temp0)
	(= temp0 0)
	(switch (param1 socketNum?)
		(1
			(if (== theFuse fuse1) (= temp0 1))
		)
		(2
			(if
				(|
					(== theFuse fuse2)
					(== theFuse fuse5)
					(== theFuse extraFuse)
				)
				(= temp0 1)
			)
		)
		(3
			(if (== theFuse fuse3) (= temp0 1))
		)
		(4
			(if (== theFuse fuse4) (= temp0 1))
		)
		(5
			(if
				(|
					(== theFuse fuse5)
					(== theFuse fuse2)
					(== theFuse extraFuse)
				)
				(= temp0 1)
			)
		)
		(6
			(if (== theFuse fuse6) (= temp0 1))
		)
	)
	(return temp0)
)

(class Fuse of Actor
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 2
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 15
		view 229
		loop 1
		cel 0
		priority 0
		underBits 0
		signal $5011
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 15
		origStep 770
		moveSpeed 0
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		socket 0
		track 0
		origX 0
		origY 0
		origZ 0
		origPri 0
		origHole 0
	)
	
	(method (init)
		(super init: &rest)
		(self
			origX: x
			origY: y
			origZ: z
			origPri: priority
			origHole: socket
			setLoop: 1
			setCel: 0
			stopUpd:
			setCycle: 0
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(if track (self x: (- mouseX 12) y: (- mouseY 8)))
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if theFuse (theFuse drop:) else (self pickup:))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(if theFuse (self drop:))
	)
	
	(method (drop)
		(= track 0)
		(self stopUpd:)
		(if (not local2)
			(theIconBar enable: 1 6)
			(if (theIconBar curInvIcon?) (theIconBar enable: 5))
			((theIconBar at: 2) cursor: 982)
			(theGame setCursor: ((theIconBar curIcon?) cursor?))
		)
		(if socket
			(switch socket
				(1
					(self y: (+ (self y?) 50) z: 50)
					(Palette palSET_INTENSITY 0 255 100)
				)
				(2
					(self y: (+ (self y?) 50) z: 50)
					(if (Btst 72)
						(Bclr 72)
						(Bset 73)
						(SolvePuzzle 202 175)
						(eureka puke: 6)
						(badFuse dispose:)
					)
				)
				(3
					(self y: (+ (self y?) 50) z: 50)
					(curRoom drawPic: 51 100)
				)
				(5
					(if (Btst 95) (Bclr 95))
					(socket5 occupied: 1)
				)
			)
			(Display
				{EXIT}
				dsCOORD
				8
				20
				dsFONT
				1605
				dsWIDTH
				30
				dsALIGN
				1
				dsCOLOR
				colGreen
			)
		else
			(theFuse setMotion: MoveTo 15 120)
		)
		(= theFuse 0)
	)
	
	(method (pickup)
		(= theFuse self)
		(self startUpd: setPri: 12)
		((theIconBar at: 2) cursor: 996)
		(theIconBar curIcon: (theIconBar at: 2))
		(theGame setCursor: 996)
		(if socket
			(= theFuse 0)
			(self posn: 15 (+ 40 (* (fuseList size?) 35)))
			(ego show:)
			((theIconBar at: 2) cursor: 982)
			(theGame setCursor: ((theIconBar curIcon?) cursor?))
			(fuseList addToFront: self)
			(switch socket
				(1
					(socket1 occupied: 0)
					(socketList addToFront: socket1)
					(self setScript: sLightsFlicker)
					(ego hide:)
				)
				(2
					(if (Btst 72) (fuseList delete: self) (self dispose:))
					(socketList addToFront: socket2)
					(socket2 occupied: 0)
					(ego hide:)
				)
				(3
					(curRoom drawPic: 512 100)
					(socket3 occupied: 0)
					(ego hide:)
					(Display
						{EXIT}
						dsCOORD
						8
						20
						dsFONT
						1605
						dsWIDTH
						30
						dsALIGN
						1
						dsCOLOR
						colGreen
					)
					(socketList addToFront: socket3)
				)
				(4
					(socket4 occupied: 0)
					(socketList addToFront: socket4)
					(self setScript: sPowerFailure)
					(ego hide:)
				)
				(5
					(socketList addToFront: socket5)
					(socket5 occupied: 0)
					(ego hide:)
				)
				(6
					(socket6 occupied: 0)
					(socketList addToFront: socket6)
					(self setScript: sLifeSupport)
					(ego hide:)
				)
			)
			(Display
				{EXIT}
				dsCOORD
				8
				20
				dsFONT
				1605
				dsWIDTH
				30
				dsALIGN
				1
				dsCOLOR
				colGreen
			)
			(= z (= socket 0))
		else
			(theIconBar disable: 1 6)
			(if (theIconBar curInvIcon?) (theIconBar disable: 5))
			(= track 1)
		)
	)
)

(class Socket of Feature
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		socketNum 0
		fusePri 0
		occupied 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (and theFuse (not occupied))
					(if (localproc_0184 self)
						(theFuse
							setPri: (self fusePri?)
							socket: (self socketNum?)
							track: 0
							setMotion: MoveTo (self x?) (self y?) theFuse
						)
						(socketList delete: self)
						(fuseList delete: theFuse)
						(= occupied 1)
					else
						(messager say: 4 0 0 1)
					)
				)
			)
			(28
				(if
					(and
						(not occupied)
						(| (== socketNum 2) (== socketNum 5))
					)
					(socketList delete: self)
					(if (and (== socketNum 2) (Btst 72))
						(Bclr 72)
						(SolvePuzzle 202 175)
						(Bset 73)
						(badFuse dispose:)
						(eureka puke: 6)
					)
					(if (and (== socketNum 5) (Btst 95)) (Bclr 95))
					(extraFuse
						init:
						x: (self x?)
						y: (self y?)
						z: (self z?)
						socket: (self socketNum?)
						track: 0
						setPri: (self fusePri?)
					)
					(= occupied 1)
					(extraFuse cue:)
					(ego put: 4)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fuseList of List
	(properties)
)

(instance socketList of List
	(properties)
)

(instance rm228 of Rm
	(properties
		picture 51
		style $000a
	)
	
	(method (init &tmp temp0)
		(curRoom setRegions: 210)
		(ego
			view: 229
			posn: 39 94
			setLoop: 0
			setCel: 0
			init:
			hide:
		)
		(fuse1 init:)
		(fuse2 init:)
		(fuse3 init:)
		(fuse4 init:)
		(fuse5 init:)
		(fuse6 init:)
		(socket1 init:)
		(socket2 init:)
		(socket3 init:)
		(socket4 init:)
		(socket5 init:)
		(socket6 init:)
		(schematic init:)
		(fuseList add:)
		(tunnelExit init:)
		(theGame handsOn:)
		(theIconBar select: (theIconBar at: 2))
		(theGame setCursor: 982)
		(theIconBar disable: 0 3 4 5)
		(switch prevRoomNum
			(225)
			(else 
				(if
					(>=
						(= temp0
							(GetNumber
								{Fuse State: \n\n\n(1) Shorted Fuse \n(2) Fuse is Okay\n}
							)
						)
						0
					)
					(= local1 temp0)
				else
					(= local1 1)
				)
				(switch local1
					(1
						(ego get: 4)
						(Bset 72)
						(Bset 60)
					)
					(2
						(Bclr 72)
						(Bset 60)
						(ego get: 4)
					)
				)
			)
		)
		(badFuse init:)
		(super init: &rest)
		(Display
			{EXIT}
			dsCOORD
			8
			20
			dsFONT
			1605
			dsWIDTH
			30
			dsALIGN
			1
			dsCOLOR
			colGreen
		)
	)
	
	(method (doit)
		(if (GameIsRestarting)
			(Display
				{EXIT}
				dsCOORD
				8
				20
				dsFONT
				1605
				dsWIDTH
				30
				dsALIGN
				1
				dsCOLOR
				colGreen
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(fuseList release: dispose:)
		(socketList release: dispose:)
		(super dispose: &rest)
	)
	
	(method (handleEvent)
		(return 0)
	)
)

(instance sExit of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local2 (= cycles 1))
			)
			(1
				(if (fuseList size?)
					(if (socketList size?)
						(messager say: 0 0 0 1 self)
					else
						(= cycles 1)
					)
				else
					(self changeState: 4)
				)
			)
			(2
				(= temp0 (fuseList at: (- (fuseList size?) 1)))
				(if (OneOf (temp0 origHole?) 2 5)
					(if (and (socket2 occupied?) (socket5 occupied?))
						(ego get: 4)
						(fuseList delete: temp0)
						(temp0 dispose:)
						(messager say: 5 0 0 1 self)
					else
						(switch (temp0 origHole?)
							(2
								(if (socket2 occupied?)
									(temp0
										origX: (fuse5 origX?)
										origY: (fuse5 origY?)
										origZ: (fuse5 origZ?)
										origHole: (fuse5 origHole?)
										origPri: (fuse5 origPri?)
									)
									(socket5 occupied: 1)
								else
									(socket2 occupied: 1)
								)
							)
							(5
								(if (socket5 occupied?)
									(temp0
										origX: (fuse2 origX?)
										origY: (fuse2 origY?)
										origZ: (fuse2 origZ?)
										origHole: (fuse2 origHole?)
										origPri: (fuse2 origPri?)
									)
									(socket2 occupied: 1)
								else
									(socket5 occupied: 1)
								)
							)
						)
						(temp0
							x: (temp0 origX?)
							y: (- (temp0 origY?) (temp0 origZ?))
							socket: (temp0 origHole?)
							setPri: (temp0 origPri?)
						)
						(temp0 drop:)
						(fuseList delete: temp0)
						(= seconds 1)
					)
				else
					(temp0
						x: (temp0 origX?)
						y: (- (temp0 origY?) (temp0 origZ?))
						socket: (temp0 origHole?)
						setPri: (temp0 origPri?)
					)
					(temp0 drop:)
					(fuseList delete: temp0)
					(= seconds 1)
				)
			)
			(3
				(if (fuseList size?) (= state (- state 2)))
				(= cycles 1)
			)
			(4
				(if (not (socket2 occupied?))
					(if (not (Btst 72))
						(messager say: 4 0 1 1 self)
						(fuse5 dispose:)
						(socket5 occupied: 0)
					else
						(= cycles 1)
					)
					(fuse2 init:)
				else
					(= cycles 1)
				)
			)
			(5
				(if (not (socket5 occupied?)) (Bset 95))
				(= cycles 1)
			)
			(6
				(curRoom newRoom: 225)
				(self dispose:)
			)
		)
	)
)

(instance sLightsFlicker of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(= register 100)
				(while (>= register 40)
					(Palette palSET_INTENSITY 0 255 register)
					(= register (- register 20))
				)
			)
			(2 (ego hide:) (= seconds 1))
			(3 (self dispose:))
		)
	)
)

(instance sPowerFailure of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame setCursor: 996)
				(= register 100)
				(while (>= register 0)
					(Palette palSET_INTENSITY 0 5 register)
					(Palette palSET_INTENSITY 7 255 register)
					(= register (- register 20))
				)
				(= cycles 1)
			)
			(1 (messager say: 3 0 0 1 self))
			(2
				(curRoom drawPic: 0)
				(EgoDead 4)
				(self dispose:)
			)
		)
	)
)

(instance sLifeSupport of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame setCursor: 996)
				(EgoDead 5)
				(self dispose:)
			)
		)
	)
)

(instance fuse1 of Fuse
	(properties
		x 121
		y 98
		z 50
		priority 8
		socket 1
	)
)

(instance fuse2 of Fuse
	(properties
		x 152
		y 97
		z 50
		priority 8
		socket 2
	)
	
	(method (init)
		(self x: 152 y: 97 z: 50 setPri: 8 socket: 2)
		(super init: &rest)
	)
)

(instance fuse3 of Fuse
	(properties
		x 188
		y 96
		z 50
		priority 8
		socket 3
	)
)

(instance fuse4 of Fuse
	(properties
		x 131
		y 57
		priority 5
		socket 4
	)
)

(instance fuse5 of Fuse
	(properties
		x 160
		y 58
		priority 5
		socket 5
	)
	
	(method (init)
		(self x: 160 y: 58 z: 0 setPri: 5 socket: 5)
		(super init: &rest)
		(if (Btst 95) (self hide:))
	)
)

(instance fuse6 of Fuse
	(properties
		x 193
		y 57
		priority 5
		socket 6
	)
)

(instance extraFuse of Fuse
	(properties
		x 152
		y 97
		z 50
		priority 8
		socket 2
	)
)

(instance socket1 of Socket
	(properties
		x 121
		y 48
		nsTop 30
		nsLeft 118
		nsBottom 56
		nsRight 151
		socketNum 1
		fusePri 8
	)
)

(instance socket2 of Socket
	(properties
		x 152
		y 47
		nsTop 32
		nsLeft 153
		nsBottom 56
		nsRight 182
		socketNum 2
		fusePri 8
	)
)

(instance socket3 of Socket
	(properties
		x 188
		y 46
		nsTop 30
		nsLeft 187
		nsBottom 56
		nsRight 216
		socketNum 3
		fusePri 8
	)
)

(instance socket4 of Socket
	(properties
		x 131
		y 57
		nsTop 57
		nsLeft 133
		nsBottom 79
		nsRight 159
		socketNum 4
		fusePri 5
	)
)

(instance socket5 of Socket
	(properties
		x 160
		y 58
		nsTop 57
		nsLeft 160
		nsBottom 89
		nsRight 195
		socketNum 5
		fusePri 5
	)
	
	(method (init)
		(super init: &rest)
		(if (Btst 95) (self occupied: 0))
	)
)

(instance socket6 of Socket
	(properties
		x 193
		y 57
		nsTop 57
		nsLeft 196
		nsBottom 89
		nsRight 240
		socketNum 6
		fusePri 5
	)
)

(instance badFuse of View
	(properties
		x 181
		y 96
		view 229
		loop 2
		priority 4
		signal $4011
	)
	
	(method (init)
		(if (Btst 72) (super init: &rest))
	)
)

(instance schematic of Feature
	(properties
		x 180
		y 104
		nsTop 91
		nsLeft 151
		nsBottom 118
		nsRight 210
		sightAngle 40
		approachX 180
		approachY 104
	)
	
	(method (doVerb theVerb &tmp temp0)
		(= temp0 0)
		(switch theVerb
			(1
				(cond 
					((InRect 151 103 171 112 mouseX mouseY) (= temp0 1))
					((InRect 173 103 191 112 mouseX mouseY) (= temp0 2))
					((InRect 192 103 210 112 mouseX mouseY) (= temp0 3))
					((InRect 151 118 171 126 mouseX mouseY) (= temp0 4))
					((InRect 173 118 191 126 mouseX mouseY) (= temp0 5))
					((InRect 192 118 210 126 mouseX mouseY) (= temp0 6))
				)
				(if temp0 (messager say: 1 0 0 temp0))
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance tunnelExit of Feature
	(properties
		x 10
		y 10
		nsTop 10
		nsBottom 45
		nsRight 39
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not theFuse)
					(if
						(or
							(fuseList size?)
							(not (socket2 occupied?))
							(not (socket5 occupied?))
						)
						(theGame handsOff:)
						(curRoom setScript: sExit)
					else
						(theGame handsOff:)
						(curRoom newRoom: 225)
					)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rogTalker of Narrator
	(properties)
	
	(method (init)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 122
			tailY: 43
			xOffset: 10
			isBottom: 1
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)
