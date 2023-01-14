;;; Sierra Script 1.0 - (do not remove this comment)
(script# WRITEFTR)
(include game.sh)
(use Main)
(use Intrface)
(use Feature)
(use Window)
(use File)
(use Actor)
(use System)


(local
	[nameString 200]
	[local200 100]
	walkString =  { verbWalk___}
	[verbString 7] = [{ verbLook___} { verbDo_____} { verbUse____} { verbTalk___} { verbHelp___} { gameVerbs__} {}]
	toScreen =  1
	drawNSRect =  1
	inited
	theType
	sightAngle =  90
)
(procedure (localproc_051e param1 &tmp newEvent temp1 temp2 newEventY newEventX newEventY_2 newEventX_2)
	(Print 948 11)
	(while (!= ((= newEvent (Event new:)) type?) 1)
		(newEvent dispose:)
	)
	(GlobalToLocal newEvent)
	(= newEventY (newEvent y?))
	(= newEventX (newEvent x?))
	(newEvent dispose:)
	(Print 948 12)
	(while (!= ((= newEvent (Event new:)) type?) 1)
		(newEvent dispose:)
	)
	(GlobalToLocal newEvent)
	(= newEventY_2 (newEvent y?))
	(= temp1
		(+
			(/ (- (= newEventX_2 (newEvent x?)) newEventX) 2)
			newEventX
		)
	)
	(= temp2 (+ (/ (- newEventY_2 newEventY) 2) newEventY))
	(param1
		x: temp1
		y: temp2
		nsLeft: newEventX
		nsTop: newEventY
		nsBottom: newEventY_2
		nsRight: newEventX_2
	)
	(if drawNSRect
		(Graph
			GDrawLine
			newEventY
			newEventX
			newEventY
			newEventX_2
			1
			15
		)
		(Graph
			GDrawLine
			newEventY_2
			newEventX
			newEventY_2
			newEventX_2
			1
			15
		)
		(Graph
			GDrawLine
			newEventY
			newEventX
			newEventY_2
			newEventX
			1
			15
		)
		(Graph
			GDrawLine
			newEventY
			newEventX_2
			newEventY_2
			newEventX_2
			1
			15
		)
		(Graph
			GShowBits
			newEventY
			newEventX
			(+ newEventY_2 1)
			(+ newEventX_2 1)
			1
		)
	)
	(newEvent dispose:)
)

(procedure (localproc_0655 param1 &tmp newEvent)
	(param1
		view: (GetNumber {View?} 0)
		loop: (GetNumber {Loop?} 0)
		cel: (GetNumber {Cel?} 0)
		signal: 16400
		priority: 15
		init:
	)
	(if (param1 respondsTo: #illegalBits)
		(param1 illegalBits: 0)
	)
	(while (!= ((= newEvent (Event new:)) type?) 1)
		(GlobalToLocal newEvent)
		(param1 posn: (newEvent x?) (newEvent y?))
		(Animate (cast elements?) 0)
		(newEvent dispose:)
	)
	(newEvent dispose:)
)

(procedure (localproc_06f9 param1)
	(File name: @sysLogPath writeString: param1 close:)
	(DisposeScript 993)
)

(procedure (localproc_0716 param1)
	(param1
		description: (GetInput @local200 40 {description?})
		sightAngle: (= sightAngle (GetNumber {sight angle?} sightAngle))
		_approachVerbs: (aprchD init: walkString)
	)
)

(procedure (localproc_0754 param1 &tmp newEvent)
	(Print 948 13)
	(while (!= ((= newEvent (Event new:)) type?) 1)
		(newEvent dispose:)
	)
	(GlobalToLocal newEvent)
	(param1
		approachX: (newEvent x?)
		approachY: (newEvent y?)
	)
	(newEvent dispose:)
)

(instance aprchD of Dialog
	(properties
		text {Approach Verbs}
	)
	
	(method (init param1 &tmp temp0 temp1)
		(= window systemWindow)
		(= nsBottom 0)
		(selectorI
			text: param1
			font: smallFont
			setSize:
			moveTo: 4 4
			state: 1
		)
		(self add: selectorI setSize:)
		(textI
			text: {Space to select, enter when done.}
			setSize: (- (- nsRight nsLeft) 8)
			moveTo: 4 4
		)
		(= temp0 (+ (textI nsBottom?) 4))
		(self eachElementDo: #move 0 temp0)
		(= temp1
			(self add: textI setSize: center: open: 4 -1 doit:)
		)
		(self dispose:)
		(if temp1
			(= temp0 0)
			(= temp1 0)
			(while (< temp0 7)
				(if (== (StrAt param1 (* temp0 13)) 62)
					(= temp1 (| temp1 (<< $0001 temp0)))
				)
				(++ temp0)
			)
		)
		(return temp1)
	)
)

(class Class_948_1
	(properties)
	
	(method (doit param1 &tmp [temp0 400] [temp400 40] [temp440 20] [temp460 40])
		(if (param1 isMemberOf: Feature)
			(Format
				@temp400
				948
				0
				(param1 nsTop?)
				(param1 nsLeft?)
				(param1 nsBottom?)
				(param1 nsRight?)
			)
		else
			(Format
				@temp400
				948
				1
				(param1 view?)
				(param1 loop?)
				(param1 cel?)
			)
		)
		(if (param1 z?)
			(Format @temp440 948 2 (param1 z?))
		else
			(= temp440 0)
		)
		(if (param1 _approachVerbs?)
			(Format
				@temp460
				948
				3
				(param1 approachX?)
				(param1 approachY?)
				(param1 _approachVerbs?)
			)
		else
			(= temp460 0)
		)
		(Format
			@temp0
			948
			4
			@nameString
			(if (== theType 2)
				(PicView name?)
			else
				((param1 superClass?) name?)
			)
			(param1 x?)
			(param1 y?)
			@temp440
			@temp400
			@local200
			(param1 sightAngle?)
			@temp460
		)
		(if toScreen
			(Print @temp0 #font 999 #title {Feature Writer V1.0})
		)
		(localproc_06f9 @temp0)
		(if (param1 isMemberOf: Feature)
			(param1 dispose:)
		else
			(param1 addToPic:)
		)
	)
	
	(method (writeList param1)
		(param1 eachElementDo: #perform self)
		(Class_948_2 doit:)
	)
)

(class Class_948_2
	(properties)
	
	(method (doit &tmp [temp0 15] temp15 newEvent theSystemWindow)
		(= theSystemWindow systemWindow)
		(= systemWindow SysWindow)
		(if (not inited)
			(= temp0 0)
			(Format @temp0 948 5 (curRoom curPic?))
			(if
			(not (GetInput @temp0 30 {Enter path and filename}))
				(return)
			)
			(Format @sysLogPath @temp0)
			(switch
				(Print
					948
					6
					#title
					{Feature Writer V1.0}
					#button
					{YES}
					1
					#button
					{NO}
					2
				)
				(0 (return))
				(1 (= drawNSRect 1))
				(2 (= drawNSRect 0))
			)
			(switch
				(Print
					948
					7
					#title
					{Feature Writer V1.0}
					#button
					{YES}
					1
					#button
					{NO}
					2
				)
				(0 (return))
				(1 (= toScreen 1))
				(2 (= toScreen 0))
			)
			(= inited 1)
		)
		(if
			(not
				(= theType
					(Print
						948
						8
						#title
						{Feature Writer V1.0}
						#button
						{Feature}
						1
						#button
						{PicView}
						2
						#button
						{View}
						3
						#button
						{Prop}
						4
						#button
						{Actor}
						5
					)
				)
			)
			(return)
		)
		(= temp15
			(
			(switch theType
				(1 Feature)
				(2 View)
				(3 View)
				(4 Prop)
				(5 Actor)
			)
				new:
			)
		)
		(GetInput @nameString 30 {Name?})
		(StrCpy @local200 @nameString)
		(localproc_0716 temp15)
		(if (== theType 1)
			(localproc_051e temp15)
		else
			(localproc_0655 temp15)
		)
		(if (temp15 _approachVerbs?) (localproc_0754 temp15))
		(if
			(Print
				948
				9
				#title
				{Feature Writer V1.0}
				#button
				{NO}
				0
				#button
				{YES}
				1
			)
			(Print 948 10)
			(while (!= ((= newEvent (Event new:)) type?) 1)
				(newEvent dispose:)
			)
			(GlobalToLocal newEvent)
			(temp15 z: (- (newEvent y?) (temp15 y?)))
			(temp15 y: (newEvent y?))
			(newEvent dispose:)
		)
		(Class_948_1 doit: temp15)
		(= systemWindow theSystemWindow)
	)
)

(instance selectorI of DSelector
	(properties
		x 13
		y 7
	)
	
	(method (handleEvent event)
		(super handleEvent: event &rest)
		(if
			(or
				(and (== (event type?) mouseDown) (event claimed?))
				(and
					(== (event type?) keyDown)
					(== (event message?) SPACEBAR)
				)
			)
			(if (== (StrAt cursor 0) 62)
				(StrAt cursor 0 32)
			else
				(StrAt cursor 0 62)
			)
			(self draw:)
			(event claimed: 1)
		)
		(event claimed?)
	)
)

(instance textI of DText
	(properties
		font 0
	)
)
