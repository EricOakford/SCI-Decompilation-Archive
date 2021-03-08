;;; Sierra Script 1.0 - (do not remove this comment)
(script# 750)
(include sci.sh)
(use Main)
(use Print)
(use Talker)
(use Scaler)
(use LoadMany)
(use Grooper)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm750 0
	rogTalker 15
)

(local
	local0
	local1 =  1
	local2
	local3 =  1
	local4
	local5
	local6
	local7
	[local8 100]
	[local108 4] = [46 180 240 300]
	[local112 15] = [40 49 58 67 76 85 94 103 112 121 130 139 148 157 166]
	[local127 3] = [45 115 300]
)
(procedure (localproc_0180 param1 param2 param3 param4 param5)
	(Graph
		grDRAW_LINE
		param2
		param1
		param4
		param3
		param5
		-1
		-1
	)
	(Graph grUPDATE_BOX param2 param1 param4 param3 1)
)

(procedure (localproc_01a5 param1 param2 param3 param4 param5)
	(localproc_0180 param1 param2 param3 param2 param5)
	(localproc_0180 param3 param2 param3 param4 param5)
	(localproc_0180 param3 param4 param1 param4 param5)
	(localproc_0180 param1 param4 param1 param2 param5)
)

(procedure (localproc_01e2 param1 param2)
	(InRect
		(+ (param2 nsLeft?) 1)
		(+ (param2 nsTop?) 1)
		(- (param2 nsRight?) 1)
		(- (param2 nsBottom?) 1)
		param1
	)
)

(procedure (localproc_021d)
	(return
		(not
			(if
				(or
					(camera1 script?)
					(camera2 script?)
					(camera3 script?)
					(mainBW script?)
				)
			else
				(curRoom script?)
			)
		)
	)
)

(procedure (localproc_0253 param1 &tmp temp0 temp1 temp2 temp3)
	(= temp2 0)
	(= temp0 0)
	(while (< temp0 12)
		(= temp1 0)
		(while (< temp1 3)
			(= temp3
				(switch temp1
					(0 130)
					(1 100)
					(2 80)
				))
			(localproc_034f
				[local108 temp1]
				[local112 temp0]
				751
				1
				0
				param1
				(++ temp2)
				20
				temp3
			)
			(++ temp1)
		)
		(++ temp0)
	)
)

(procedure (localproc_02b6 param1 &tmp temp0 temp1 temp2 temp3 temp4)
	(= temp2 0)
	(= temp0 0)
	(while (< temp0 14)
		(= temp1 0)
		(while (< temp1 2)
			(= temp3
				(switch temp1
					(0 56)
					(1 230)
				))
			(localproc_034f
				[local127 temp1]
				[local112 temp0]
				750
				4
				0
				param1
				(++ temp2)
				20
				temp3
			)
			(if (> temp2 27) (= temp0 50) (= temp1 50))
			(++ temp1)
		)
		(++ temp0)
	)
)

(procedure (localproc_031e param1 &tmp temp0 temp1 temp2 [temp3 2])
	(= temp2 0)
	(= temp0 0)
	(while (< temp0 12)
		(localproc_034f
			50
			[local112 temp0]
			752
			3
			0
			param1
			(++ temp2)
			20
			270
		)
		(++ temp0)
	)
)

(procedure (localproc_034f param1 param2 param3 param4 param5 param6 param7 param8 param9 param10)
	(Message
		msgGET
		param3
		param4
		param5
		param6
		param7
		@local8
	)
	(if (== argc 10)
		(Display
			@local8
			dsCOORD
			param1
			param2
			dsFONT
			1605
			dsWIDTH
			param9
			dsCOLOR
			param8
			dsBACKGROUND
			param10
		)
	else
		(Display
			@local8
			dsCOORD
			param1
			param2
			dsFONT
			1605
			dsWIDTH
			param9
			dsCOLOR
			param8
		)
	)
)

(instance rm750 of Rm
	(properties)
	
	(method (init)
		(LoadMany 143 number)
		(self setRegions: 31)
		(SolvePuzzle 231 10)
		(if (Btst 22)
			(ego
				view: 617
				signal: 16384
				init:
				setLoop: -1
				illegalBits: 0
				setPri: 15
				noun: 19
				moveSpeed: 6
				setScale: 0
				setCycle: Walk
				setStep: 6 6
				cycleSpeed: 2
				setLoop: Grooper
			)
			(if (not (Btst 121)) (ego posn: 50 50) (Bset 121))
			(walkHandler addToFront: self)
		)
		(camera1 signal: 16400)
		(camera2 signal: 16400)
		(camera3 signal: 16400)
		(= local5 0)
		(while (< local5 (addToPics size?))
			(= local6 (addToPics at: local5))
			(local6 signal: (& (local6 signal?) $ffdf))
			(++ local5)
		)
		(addToPics delete: ego)
		(addToPics eachElementDo: #dispose)
		(theGame handsOn:)
		(= picture
			(switch gRegister
				(0 119)
				(4 118)
				(5 119)
				(6 119)
				(7 119)
				(8 119)
				(1 113)
				(2 110)
				(3 112)
			)
		)
		(= noun
			(switch gRegister
				(0 7)
				(4 7)
				(5 7)
				(6 7)
				(7 7)
				(8 7)
				(1 8)
				(2 8)
				(3 8)
			)
		)
		(= style -32668)
		(super init:)
		(if (not (Btst 22))
			(theIconBar curIcon: (theIconBar at: 2))
			(theGame setCursor: 982)
			(theIconBar disable: 4 7 5 6 0 3)
		else
			(theIconBar disable: 4 7 5 2 6 3)
		)
		(if (and (== picture 119) (!= gRegister 0))
			(header
				init:
				cel:
				(switch gRegister
					(5 0)
					(6 1)
					(7 3)
					(8 2)
				)
			)
		)
		(if (OneOf picture 110 112 113)
			(Palette palSET_FROM_RESOURCE (curRoom picture?) 2)
		)
		(switch gRegister
			(0
				(LoadMany 128 625)
				(curRoom setScript: sDanger)
			)
			(4
				(if (Btst 120)
					(curRoom setScript: sDoMainMenu)
					(= local3 1)
				else
					(Bset 120)
					(curRoom setScript: sReboot)
				)
			)
			(5
				(curRoom setScript: sDoSystems)
			)
			(6 (curRoom setScript: sDoLog))
			(7
				(curRoom setScript: sDoProjects)
			)
			(8
				(curRoom setScript: sDoAccounting)
			)
			(1
				(curRoom setScript: sDoSecurity3)
			)
			(2
				(curRoom setScript: sDoSecurity2)
			)
			(3
				(curRoom setScript: sDoSecurity1)
			)
		)
	)
	
	(method (doit)
		(switch gRegister
			(3
				(Palette palANIMATE 225 233 -1)
				(Palette palANIMATE 234 240 -1)
			)
			(0
				(Palette palANIMATE 241 243 20 243 245 20)
			)
		)
		(if (== (theIconBar at: 0) (theIconBar curIcon?))
			(cond 
				((InRect 10 10 296 188 mouseX mouseY)
					(if
					(== (((theIconBar curIcon?) cursor?) view?) 987)
						(((theIconBar curIcon?) cursor?) view: 980)
						(theGame setCursor: ((theIconBar curIcon?) cursor?))
					)
				)
				(
				(!= (((theIconBar curIcon?) cursor?) view?) 987)
					(((theIconBar curIcon?) cursor?) view: 987)
					(theGame setCursor: ((theIconBar curIcon?) cursor?))
				)
			)
		)
		(if (== (theIconBar at: 2) (theIconBar curIcon?))
			(cond 
				((InRect 10 10 296 188 mouseX mouseY)
					(if (== ((theIconBar curIcon?) cursor?) 988)
						((theIconBar curIcon?) cursor: 982)
						(theGame setCursor: ((theIconBar curIcon?) cursor?))
					)
				)
				((!= ((theIconBar curIcon?) cursor?) 988)
					((theIconBar curIcon?) cursor: 988)
					(theGame setCursor: ((theIconBar curIcon?) cursor?))
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(if (Btst 22)
			(((theIconBar at: 0) cursor?) view: 980)
		else
			((theIconBar at: 2) cursor: 982)
		)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if
				(== (((theIconBar curIcon?) cursor?) view?) 987)
					(user canControl: 1)
					(user canInput: 1)
					(curRoom newRoom: 790)
				else
					(ego setMotion: MoveTo mouseX mouseY)
					(++ local1)
				)
			)
			(4
				(if (== ((theIconBar curIcon?) cursor?) 988)
					(curRoom newRoom: 790)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sDanger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(restartIcon init:)
				(Graph grFILL_BOX 103 40 165 313 1 9 -1 -1)
				(Graph grREDRAW_BOX 103 40 165 313)
				(= cycles 5)
			)
			(1
				(Message msgGET 750 2 0 0 2 @local8)
				(Display
					@local8
					dsALIGN
					1
					dsCOORD
					33
					60
					dsFONT
					2407
					dsWIDTH
					280
					dsCOLOR
					12
				)
				(Message msgGET 750 2 0 0 3 @local8)
				(Display
					@local8
					dsALIGN
					1
					dsCOORD
					28
					109
					dsFONT
					2510
					dsWIDTH
					290
					dsCOLOR
					12
				)
				(Message msgGET 750 2 0 0 4 @local8)
				(Display
					@local8
					dsALIGN
					1
					dsCOORD
					62
					125
					dsFONT
					4115
					dsWIDTH
					220
					dsCOLOR
					12
				)
				(Message msgGET 750 2 0 0 5 @local8)
				(Display
					@local8
					dsALIGN
					1
					dsCOORD
					61
					145
					dsFONT
					2510
					dsWIDTH
					220
					dsCOLOR
					12
				)
				(= cycles 1)
			)
			(2 (= cycles 4))
			(3 (self dispose:))
		)
	)
)

(instance sSelectButton of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local2 1)
				(client setCel: 1)
				(= seconds 2)
			)
			(1
				(client setCel: 0)
				(= cycles 1)
			)
			(2
				(= local2 0)
				(curRoom setScript: sDoNextScreen 0 register)
				(self dispose:)
			)
		)
	)
)

(instance sReboot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local7 1)
				(theMusic1 pause: 1)
				(theMusic2 number: 618 setLoop: 1 play: self)
				(swoosh init:)
				(weplygod init:)
				(soYoudo init:)
			)
			(1
				(theMusic1 pause: 0)
				(starBurst init: setCycle: End self)
			)
			(2
				(wEyes init: setCycle: End self)
			)
			(3 (= seconds 1))
			(4
				(swoosh setCycle: End self)
				(starBurst dispose:)
				(wEyes dispose:)
			)
			(5
				(swoosh setMotion: MoveTo 340 48 self)
			)
			(6 (= seconds 1))
			(7
				(DrawPic 118 dpOPEN_PIXELATION)
				(swoosh dispose:)
				(weplygod dispose:)
				(soYoudo dispose:)
				(= cycles 5)
			)
			(8
				(curRoom setScript: sDoMainMenu)
				(self dispose:)
			)
		)
	)
)

(instance sDoNextScreen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 124 setLoop: 1 play:)
				(cast delete: dome1)
				(cast delete: dome2)
				(cast delete: dome3)
				(cast eachElementDo: #addToPic)
				(cast eachElementDo: #dispose)
				(= gRegister register)
				(= cycles 1)
			)
			(1
				(curRoom init:)
				(self dispose:)
			)
		)
	)
)

(instance sDoSystems of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(backToMain init:)
				(leftTop init: addToPic:)
				(piece1 init: addToPic:)
				(dome1 init: hide:)
				(dome2 init: hide:)
				(dome3 init: hide:)
				(rightBottom init: addToPic:)
				(leftBottom init: addToPic:)
				(self dispose:)
			)
		)
	)
)

(instance sSelectDome of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(securityIcon dispose:)
				(dome1 hide:)
				(dome2 hide:)
				(dome3 hide:)
				(= ticks 5)
			)
			(1
				(switch register
					(dome1
						(dome1 show:)
						(localproc_034f 49 131 752 1 0 0 1 31 86 67)
					)
					(dome2
						(dome2 show:)
						(localproc_034f 49 131 752 1 0 0 2 31 86 67)
					)
					(dome3
						(dome3 show:)
						(securityIcon init:)
						(localproc_034f 49 131 752 1 0 0 3 31 86 67)
					)
				)
				(theMusic2 number: 124 setLoop: 1 play:)
				(= ticks 5)
			)
			(2 (self dispose:))
		)
	)
)

(instance sDoSecurity1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(user canControl: 0)
				(user canInput: 0)
				(camera2 init:)
				(camera1 init:)
				(mainBW init:)
				(bigDoor init: addToPic:)
				(= ticks 1)
			)
			(1
				(if (Btst 22)
					(= ticks 1)
				else
					(cliffy init: setCycle: End)
					(user canControl: 1)
					(user canInput: 1)
					(self dispose:)
					(= seconds 2)
				)
			)
			(2
				(if (Btst 26)
					(myWD40 init: loop: 3)
					(myWD40Head init: cycleSpeed: 12 setScript: sWoscillate)
					(myCliffy
						init:
						loop: 2
						cycleSpeed: 6
						setScript: sCycleCliffy
					)
					(= cycles 2)
				else
					(myWD40 init: cycleSpeed: 6 setCycle: End self)
					(myCliffy init: cycleSpeed: 20 setCycle: End)
				)
			)
			(3
				(if (Btst 26)
					(user canControl: 1)
					(user canInput: 1)
					(self dispose:)
				else
					(myWD40Head init: cycleSpeed: 12 setScript: sWoscillate)
					(myWD40 loop: 3)
					(myCliffy loop: 2 cycleSpeed: 6 setScript: sCycleCliffy)
					(= seconds 1)
				)
			)
			(4
				(ego setMotion: MoveTo 100 160 self)
			)
			(5
				(ego setMotion: MoveTo 170 85 self)
			)
			(6
				(messager say: 1 0 0 0 self 753)
			)
			(7
				(Bset 26)
				(user canControl: 1)
				(user canInput: 1)
				(self dispose:)
			)
		)
	)
)

(instance sDoSecurity2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Palette palSET_FROM_RESOURCE (curRoom picture?) 2)
				(user canControl: 0)
				(user canInput: 0)
				(camera1 init:)
				(camera3 init:)
				(if (not (ego has: 10)) (comm init:))
				(mainBW init:)
				(= ticks 1)
			)
			(1
				(Palette palSET_FROM_RESOURCE (curRoom picture?) 2)
				(user canControl: 1)
				(user canInput: 1)
				(self dispose:)
			)
		)
	)
)

(instance sDoSecurity3 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Palette palSET_FROM_RESOURCE (curRoom picture?) 2)
				(user canControl: 0)
				(user canInput: 0)
				(camera3 init:)
				(camera2 init:)
				(lever init:)
				(mainBW init:)
				(if (Btst 27)
					(user canControl: 1)
					(user canInput: 1)
					(self dispose:)
				else
					(rogTrashFly init:)
				)
				(= ticks 1)
			)
			(1
				(Palette palSET_FROM_RESOURCE (curRoom picture?) 2)
				(rogTrashFly setCycle: End self)
			)
			(2
				(Bset 27)
				(rogTrashFly dispose:)
				(user canControl: 1)
				(user canInput: 1)
				(self dispose:)
			)
		)
	)
)

(instance sDoProjects of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(backToMain init:)
				(localproc_01a5 44 20 308 34 15)
				(localproc_01a5 44 36 308 146 15)
				(localproc_01a5 42 18 310 148 15)
				(localproc_034f 57 24 752 2 0 0 local3 15 200)
				(localproc_031e local3)
				(if (!= local3 1) (prefIcon init:))
				(if (== local3 9)
					(Bset 82)
					(Bset 102)
					(SolvePuzzle 233 20)
				)
				(if (!= local3 10) (nextIcon init:))
				(= cycles 2)
			)
			(1 (self dispose:))
		)
	)
)

(instance sDoAccounting of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(backToMain init:)
				(localproc_01a5 44 20 308 34 15)
				(localproc_01a5 44 36 171 146 15)
				(localproc_01a5 171 36 222 146 15)
				(localproc_01a5 222 36 308 146 15)
				(localproc_01a5 42 18 310 148 15)
				(localproc_034f 100 24 751 1 0 0 2 15 200)
				(SolvePuzzle 232 15)
				(localproc_0253 local3)
				(if (!= local3 1) (prefIcon init:))
				(if (!= local3 3) (nextIcon init:))
				(= cycles 2)
			)
			(1 (self dispose:))
		)
	)
)

(instance sDoLog of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(backToMain init:)
				(localproc_01a5 44 36 81 176 15)
				(localproc_01a5 81 36 308 176 15)
				(localproc_01a5 42 18 310 178 15)
				(localproc_01a5 44 20 308 34 15)
				(localproc_034f 57 24 750 3 0 0 1 15 100)
				(localproc_034f 157 24 750 3 0 0 2 15 100)
				(localproc_02b6 local3)
				(if (!= local3 1) (prefIcon init:))
				(if (!= local3 4) (nextIcon init:))
				(self dispose:)
			)
		)
	)
)

(instance sDoMainMenu of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(but1 init:)
				(but2 init:)
				(but3 init:)
				(but4 init:)
				(= cycles 3)
			)
			(1 (self dispose:))
		)
	)
)

(instance swoosh of Actor
	(properties
		x 239
		y 58
		view 625
		priority 3
		signal $6010
		cycleSpeed 10
		xStep 10
		moveSpeed 0
	)
)

(instance starBurst of Prop
	(properties
		x 242
		y 61
		view 625
		loop 1
		cel 3
		signal $4010
		cycleSpeed 10
	)
)

(instance security of Prop
	(properties
		x 7
		y 79
		view 625
		loop 11
		signal $4010
	)
)

(instance backToMain of Prop
	(properties
		y 78
		view 627
		loop 1
		priority 4
		signal $4010
	)
	
	(method (doit)
		(if
			(and
				(Btst 22)
				(not local2)
				(localproc_01e2 ego self 1)
			)
			(self setScript: sSelectButton 0 4)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(if (not (Btst 22))
			(switch theVerb
				(4
					(curRoom setScript: sDoNextScreen 0 4)
				)
			)
		)
	)
)

(instance wEyes of Prop
	(properties
		x 240
		y 61
		view 625
		loop 2
		cel 4
		priority 4
		signal $4010
	)
)

(instance piece1 of View
	(properties
		x 48
		y 23
		view 627
		priority 4
		signal $4010
	)
)

(instance leftTop of View
	(properties
		x 185
		y 23
		view 627
		cel 1
		priority 4
		signal $4010
	)
)

(instance rightBottom of View
	(properties
		x 48
		y 115
		view 627
		cel 2
		signal $4010
	)
)

(instance leftBottom of View
	(properties
		x 184
		y 110
		view 627
		cel 3
		priority 4
		signal $4010
	)
)

(instance topWords of View
	(properties
		x 64
		y 12
		view 627
		loop 13
		priority 4
		signal $4010
	)
)

(instance securityIcon of Prop
	(properties
		y 93
		view 627
		loop 3
		priority 4
		signal $4010
	)
	
	(method (doit)
		(if
			(and
				(Btst 22)
				(not local2)
				(localproc_01e2 ego self 1)
			)
			(self setScript: sSelectButton 0 1)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(if (not (Btst 22))
			(switch theVerb
				(4
					(self setScript: sSelectButton 0 1)
				)
			)
		)
	)
)

(instance sideWord3 of View
	(properties
		y 109
		view 627
		loop 3
		priority 4
		signal $4010
	)
)

(instance but1 of Prop
	(properties
		x 47
		y 140
		view 625
		loop 4
		signal $4000
	)
	
	(method (doit)
		(if (and (Btst 22) (not local2))
			(if (localproc_01e2 ego but1 1)
				(but1 setScript: sSelectButton 0 5)
			)
			(if (localproc_01e2 ego but2 1)
				(but2 setScript: sSelectButton 0 6)
			)
			(if (localproc_01e2 ego but3 1)
				(but3 setScript: sSelectButton 0 7)
			)
			(if (localproc_01e2 ego but4 1)
				(but4 setScript: sSelectButton 0 8)
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(if (not (Btst 22))
			(switch theVerb
				(4
					(but1 setScript: sSelectButton 0 5)
				)
			)
		)
	)
)

(instance but2 of Prop
	(properties
		x 103
		y 140
		view 625
		loop 5
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (not (Btst 22))
			(switch theVerb
				(4
					(but2 setScript: sSelectButton 0 6)
				)
			)
		)
	)
)

(instance but3 of Prop
	(properties
		x 173
		y 140
		view 625
		loop 6
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (not (Btst 22))
			(switch theVerb
				(4
					(but3 setScript: sSelectButton 0 7)
				)
			)
		)
	)
)

(instance but4 of Prop
	(properties
		x 227
		y 140
		view 625
		loop 7
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (not (Btst 22))
			(switch theVerb
				(4
					(but4 setScript: sSelectButton 0 8)
				)
			)
		)
	)
)

(instance bigDoor of View
	(properties
		x 14
		y 102
		view 610
		signal $4010
	)
)

(instance weplygod of Prop
	(properties
		x 122
		y 116
		view 625
		loop 12
		signal $4010
	)
)

(instance soYoudo of Prop
	(properties
		x 97
		y 128
		view 625
		loop 13
		signal $4010
	)
)

(instance camera1 of Prop
	(properties
		x 17
		y 117
		view 613
		loop 2
		priority 14
		signal $4010
	)
	
	(method (doit)
		(if
			(and
				(Btst 22)
				(localproc_021d)
				(not local2)
				(localproc_01e2 ego self 1)
			)
			(self setScript: sSelectButton 0 1)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(if (not (Btst 22))
			(switch theVerb
				(4
					(self setScript: sSelectButton 0 1)
				)
			)
		)
	)
)

(instance camera2 of Prop
	(properties
		x 17
		y 137
		view 613
		loop 3
		priority 14
		signal $4010
	)
	
	(method (doit)
		(if
			(and
				(Btst 22)
				(localproc_021d)
				(not local2)
				(localproc_01e2 ego self 1)
			)
			(self setScript: sSelectButton 0 2)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(if (not (Btst 22))
			(switch theVerb
				(4
					(self setScript: sSelectButton 0 2)
				)
			)
		)
	)
)

(instance camera3 of Prop
	(properties
		x 17
		y 157
		view 613
		loop 4
		priority 14
		signal $4010
	)
	
	(method (doit)
		(if
			(and
				(Btst 22)
				(localproc_021d)
				(not local2)
				(localproc_01e2 ego self 1)
			)
			(self setScript: sSelectButton 0 3)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(if (not (Btst 22))
			(switch theVerb
				(4
					(self setScript: sSelectButton 0 3)
				)
			)
		)
	)
)

(instance mainBW of Prop
	(properties
		x 17
		y 177
		view 613
		loop 5
		priority 14
		signal $4010
	)
	
	(method (doit)
		(if
			(and
				(Btst 22)
				(localproc_021d)
				(not local2)
				(localproc_01e2 ego self 1)
			)
			(self setScript: sSelectButton 0 4)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(if (not (Btst 22))
			(switch theVerb
				(4
					(self setScript: sSelectButton 0 4)
				)
			)
		)
	)
)

(instance myWD40Head of Prop
	(properties
		x 208
		y 72
		noun 1
		view 609
		loop 4
		cel 2
		signal $4000
		cycleSpeed 40
	)
	
	(method (init)
		(super init:)
		(self setScale: Scaler 124 55 167 80)
	)
)

(instance myCliffy of Prop
	(properties
		x 179
		y 104
		noun 1
		view 609
		cel 11
		signal $4000
		cycleSpeed 110
	)
	
	(method (init)
		(super init:)
		(self setScale: Scaler 124 55 167 80)
	)
)

(instance myWD40 of Actor
	(properties
		x 202
		y 102
		noun 1
		view 609
		loop 1
		cel 15
		signal $4000
	)
	
	(method (init)
		(super init:)
		(self setScale: Scaler 124 55 167 80)
	)
)

(instance rogTrashFly of Prop
	(properties
		x 114
		y 112
		noun 9
		view 613
		priority 14
		signal $4010
	)
)

(instance prefIcon of Actor
	(properties
		y 90
		view 627
		loop 6
		signal $4000
	)
	
	(method (doit)
		(if
			(and
				(Btst 22)
				(localproc_01e2 ego self 1)
				(< 1 local1)
				(not (curRoom script?))
				(not local2)
			)
			(-- local3)
			(self setScript: sSelectButton 0 gRegister)
			(= local1 0)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(if (not (Btst 22))
			(switch theVerb
				(4
					(-- local3)
					(curRoom setScript: sDoNextScreen 0 gRegister)
				)
			)
		)
	)
)

(instance nextIcon of Actor
	(properties
		y 104
		view 627
		loop 8
		signal $4000
	)
	
	(method (doit)
		(if
			(and
				(Btst 22)
				(localproc_01e2 ego self 1)
				(< 1 local1)
				(not (curRoom script?))
				(not local2)
			)
			(= local1 0)
			(++ local3)
			(self setScript: sSelectButton 0 gRegister)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(++ local3)
				(curRoom setScript: sDoNextScreen 0 gRegister)
			)
		)
	)
)

(instance restartIcon of Prop
	(properties
		y 71
		view 627
		loop 7
	)
	
	(method (doit)
		(if
			(and
				(Btst 22)
				(not local2)
				(localproc_01e2 ego self 1)
			)
			(self setScript: sSelectButton 0 4)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(if (not (Btst 22))
			(switch theVerb
				(4
					(self setScript: sSelectButton 0 4)
				)
			)
		)
	)
)

(instance dome1 of Prop
	(properties
		x 138
		y 268
		z 200
		onMeCheck $0400
		view 624
		priority 10
		signal $4010
	)
	
	(method (doit)
		(if
			(and
				(Btst 22)
				(not (curRoom script?))
				(& (self signal?) $0080)
				(& onMeCheck (ego onControl: 1))
			)
			(curRoom setScript: sSelectDome 0 self)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(return
			(if
				(and
					(user canControl:)
					(self onMe: event)
					(& (event type?) evVERB)
				)
				(self doVerb: (event message?))
				(return (event claimed: 1))
			else
				(return 0)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if
				(and (& (self signal?) $0080) (not (curRoom script?)))
					(curRoom setScript: sSelectDome 0 self)
				)
			)
			(1
				(Printf
					{%d %d %d %d}
					(Btst 22)
					(not (curRoom script?))
					(& (self signal?) $0080)
					(& onMeCheck (ego onControl: 1))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe theObjOrX theY &tmp temp0 temp1)
		(if (IsObject theObjOrX)
			(= temp0 (theObjOrX x?))
			(= temp1 (theObjOrX y?))
		else
			(= temp0 theObjOrX)
			(= temp1 theY)
		)
		(return
			(if
				(and
					(<= nsLeft temp0)
					(<= temp0 nsRight)
					(<= nsTop temp1)
				)
				(<= temp1 nsBottom)
			else
				0
			)
		)
	)
)

(instance dome2 of Prop
	(properties
		x 220
		y 267
		z 199
		onMeCheck $0002
		view 624
		priority 10
		signal $4010
	)
	
	(method (doit)
		(if
			(and
				(Btst 22)
				(not (curRoom script?))
				(& (self signal?) $0080)
				(& onMeCheck (ego onControl: 1))
			)
			(curRoom setScript: sSelectDome 0 self)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(return
			(if
				(and
					(user canControl:)
					(self onMe: event)
					(& (event type?) evVERB)
				)
				(self doVerb: (event message?))
				(return (event claimed: 1))
			else
				(return 0)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if
				(and (& (self signal?) $0080) (not (curRoom script?)))
					(curRoom setScript: sSelectDome 0 self)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe theObjOrX theY &tmp temp0 temp1)
		(if (IsObject theObjOrX)
			(= temp0 (theObjOrX x?))
			(= temp1 (theObjOrX y?))
		else
			(= temp0 theObjOrX)
			(= temp1 theY)
		)
		(return
			(if
				(and
					(<= nsLeft temp0)
					(<= temp0 nsRight)
					(<= nsTop temp1)
				)
				(<= temp1 nsBottom)
			else
				0
			)
		)
	)
)

(instance dome3 of Prop
	(properties
		x 181
		y 333
		z 200
		onMeCheck $1000
		view 624
		signal $4000
	)
	
	(method (doit)
		(if
			(and
				(Btst 22)
				(not (curRoom script?))
				(& (self signal?) $0080)
				(& onMeCheck (ego onControl: 1))
			)
			(curRoom setScript: sSelectDome 0 self)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(return
			(if
				(and
					(user canControl:)
					(self onMe: event)
					(& (event type?) evVERB)
				)
				(self doVerb: (event message?))
				(return (event claimed: 1))
			else
				(return 0)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if
				(and (& (self signal?) $0080) (not (curRoom script?)))
					(curRoom setScript: sSelectDome 0 self)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe theObjOrX theY &tmp temp0 temp1)
		(if (IsObject theObjOrX)
			(= temp0 (theObjOrX x?))
			(= temp1 (theObjOrX y?))
		else
			(= temp0 theObjOrX)
			(= temp1 theY)
		)
		(return
			(if
				(and
					(<= nsLeft temp0)
					(<= temp0 nsRight)
					(<= nsTop temp1)
				)
				(<= temp1 nsBottom)
			else
				0
			)
		)
	)
)

(instance header of View
	(properties
		x 132
		y 12
		view 627
		loop 13
		signal $4000
	)
)

(instance lever of Prop
	(properties
		x 121
		y 94
		view 613
		loop 1
		priority 8
		signal $4010
	)
)

(instance rogTalker of Narrator
	(properties
		talkWidth 150
	)
	
	(method (init)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 150
			tailY: 90
			xOffset: -5
			isBottom: 0
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance sWoscillate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1 (client setCycle: End self))
			(2 (= seconds 2))
			(3 (client setCycle: Beg self))
			(4 (= cycles 1) (= state -1))
		)
	)
)

(instance sCycleCliffy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(client setCycle: CT 3 1 self)
			)
			(2 (= seconds 2))
			(3 (client setCycle: End self))
			(4 (= seconds 2))
			(5
				(client setCycle: CT 3 -1 self)
			)
			(6 (= seconds 2))
			(7 (client setCycle: Beg self))
			(8 (= cycles 2) (= state -1))
		)
	)
)

(instance comm of Prop
	(properties
		x 224
		y 125
		noun 5
		view 609
		loop 8
		signal $4000
	)
)

(instance cliffy of Actor
	(properties
		x 70
		y 154
		view 609
		loop 6
	)
)
