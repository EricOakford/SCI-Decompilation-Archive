;;; Sierra Script 1.0 - (do not remove this comment)
(script# 706)
(include sci.sh)
(use Main)
(use DScript)
(use PolyPath)
(use Motion)
(use Actor)

(public
	viewCartridge 0
)

(local
	[str 251]
)
(instance screen of Prop
	(properties
		x 152
		y 135
		view 161
		loop 1
		cycleSpeed 6
	)
)

(instance viewCartridge of DScript
	(properties)
	
	(method (doit)
		(super doit:)
		(-- counter)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(UnLoad 129 99)
		(DisposeScript 706)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theMusic fade:)
				(= ticks 18)
			)
			(1
				(ego ignoreActors: 1 setMotion: PolyPath 139 178 self)
			)
			(2 (ego setHeading: 45 self))
			(3
				(screen init: setLoop: 1 cel: 0 setCycle: End self)
			)
			(4
				(cast eachElementDo: #hide)
				(cast eachElementDo: #stopUpd)
				((ScriptID 703 14) dispose:)
				((ScriptID 703 15) dispose:)
				(curRoom drawPic: 99 10)
				(Display
					706
					0
					dsALIGN
					1
					dsWIDTH
					165
					dsCOORD
					78
					40
					dsCOLOR
					colLYellow
				)
				(= seconds 3)
			)
			(5
				(curRoom drawPic: 99 13)
				(theMusic2 number: 413 loop: -1 play:)
				(Display
					706
					1
					dsALIGN
					1
					dsWIDTH
					165
					dsCOORD
					78
					40
					dsCOLOR
					colLYellow
				)
				(= seconds 10)
			)
			(6
				(curRoom drawPic: 99 13)
				(Display
					706
					2
					dsALIGN
					1
					dsWIDTH
					165
					dsCOORD
					78
					40
					dsCOLOR
					colLYellow
				)
				(= seconds 16)
			)
			(7
				(curRoom drawPic: 99 13)
				(Display
					706
					3
					dsALIGN
					1
					dsWIDTH
					165
					dsCOORD
					78
					40
					dsCOLOR
					colLYellow
				)
				(= seconds 10)
			)
			(8
				(curRoom drawPic: 99 13)
				(Display
					706
					4
					dsALIGN
					1
					dsWIDTH
					165
					dsCOORD
					78
					40
					dsCOLOR
					colLYellow
				)
				(= seconds 12)
			)
			(9
				(curRoom drawPic: 99 13)
				(Display
					706
					5
					dsALIGN
					1
					dsWIDTH
					165
					dsCOORD
					78
					40
					dsCOLOR
					colLYellow
				)
				(= seconds 12)
			)
			(10
				(curRoom drawPic: 99 13)
				(Display
					706
					6
					dsALIGN
					1
					dsWIDTH
					165
					dsCOORD
					78
					40
					dsCOLOR
					colLYellow
				)
				(= seconds 12)
			)
			(11
				(curRoom drawPic: 99 13)
				(Display
					706
					7
					dsALIGN
					1
					dsWIDTH
					165
					dsCOORD
					78
					40
					dsCOLOR
					colLYellow
				)
				(= seconds 12)
			)
			(12
				(curRoom drawPic: 99 13)
				(Display
					706
					8
					dsALIGN
					1
					dsWIDTH
					165
					dsCOORD
					78
					40
					dsCOLOR
					colLYellow
				)
				(= seconds 10)
			)
			(13
				(curRoom drawPic: 99 13)
				(SolvePuzzle 5 152)
				(Format @str 706 9 selfDestructCode)
				(Display
					@str
					dsALIGN
					1
					dsWIDTH
					165
					dsCOORD
					78
					40
					dsCOLOR
					colLYellow
				)
				(= seconds 20)
			)
			(14
				(curRoom drawPic: 99 13)
				(Display
					706
					10
					dsALIGN
					1
					dsWIDTH
					165
					dsCOORD
					78
					40
					dsCOLOR
					colLYellow
				)
				(= seconds 16)
			)
			(15
				(curRoom drawPic: 99 13)
				(Display
					706
					11
					dsALIGN
					1
					dsWIDTH
					165
					dsCOORD
					78
					40
					dsCOLOR
					colLYellow
				)
				(= seconds 4)
			)
			(16
				(curRoom drawPic: 99 13)
				(theMusic2 fade: loop: 0)
				(theMusic play: 0 fade: 127 25 10 0)
				(cast eachElementDo: #show)
				(cast eachElementDo: #startUpd)
				(ego ignoreActors: 0)
				((ScriptID 703 14) init: activate:)
				((ScriptID 703 15) init: activate:)
				((ego _head?) hide:)
				(curRoom drawPic: 61 10)
				(screen setLoop: 2 cel: 0 cycleSpeed: 20 setCycle: Fwd)
				(= seconds 3)
			)
			(17
				(screen dispose:)
				(= seconds 1)
			)
			(18 (HandsOn) (self dispose:))
		)
	)
	
	(method (handleEvent event)
		(if clrByKey
			(if (<= counter 0)
				(event claimed: 1)
				(= seconds 0)
				(= cycles 1)
			)
		else
			(super handleEvent: event)
		)
	)
)
