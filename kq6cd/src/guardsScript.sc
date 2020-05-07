;;; Sierra Script 1.0 - (do not remove this comment)
(script# 802)
(include sci.sh)
(use Main)
(use Stair)
(use Print)
(use Motion)
(use Actor)
(use System)

(public
	guardsScript 0
)

(local
	egoX
	egoY
)
(instance guardsScript of Script
	(properties)
	
	(method (dispose)
		(theGame handsOn:)
		(super dispose:)
		(DisposeScript 802)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager
					say: 6 1 (+ 24 ((ScriptID 80 0) tstFlag: 709 32)) 1 self
				)
			)
			(1
				(= egoX (ego x?))
				(= egoY (ego y?))
				(ego
					normal: 0
					view: 802
					loop: 0
					cel: 0
					posn: 297 173
					setScale: 0
					scaleX: 128
					scaleY: 128
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(2
				(cast eachElementDo: #startUpd)
				(= cycles 4)
			)
			(3
				(theIconBar disable:)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 802 10)
				(background addToPic:)
				(guard1 init: stopUpd:)
				(guard2 init: stopUpd:)
				(if (not ((ScriptID 80 0) tstFlag: 709 32))
					(theGame givePoints: 2)
					(saladin init: stopUpd:)
				)
				(= cycles 4)
			)
			(4
				(theIconBar enable:)
				(if (not ((ScriptID 80 0) tstFlag: 709 32))
					(self setScript: overHearGuards self)
				else
					(self setScript: guardsNotHere self)
				)
			)
			(5
				(background dispose:)
				(guard1 dispose: delete:)
				(guard2 dispose: delete:)
				(saladin dispose: delete:)
				(proc800_1)
				(ego setCycle: Beg self)
			)
			(6
				(theIconBar enable:)
				(ego posn: egoX egoY reset: 0)
				(= cycles 2)
			)
			(7 (self dispose:))
		)
	)
)

(instance overHearGuards of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 80 0) setFlag: 709 32)
				(= cycles 3)
			)
			(1
				(messager say: 6 1 24 2 self oneOnly: 0)
			)
			(2
				(Print
					font: userFont
					width: 250
					posn: 20 139
					addText:
						{He was speakin' to that door--black magic, is what I say! I heard him say 'Ali', but then Bay came up and started yappin at me.}
					modeless: 1
					init:
				)
				(= seconds 10)
			)
			(3
				(if modelessDialog (modelessDialog dispose:))
				(= cycles 10)
			)
			(4 (self dispose:))
		)
	)
)

(instance guardsNotHere of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(messager say: 6 1 25 2 self oneOnly: 0)
			)
			(2 (self dispose:))
		)
	)
)

(instance background of View
	(properties
		x 77
		y 54
		view 803
		signal $4010
	)
)

(instance guard1 of View
	(properties
		x 144
		y 125
		view 724
		loop 4
		cel 3
		scaleSignal $0001
		scaleX 147
		scaleY 147
	)
)

(instance guard2 of View
	(properties
		x 127
		y 110
		view 726
		loop 4
		scaleSignal $0001
		scaleX 121
		scaleY 121
	)
)

(instance saladin of View
	(properties
		x 167
		y 110
		view 736
		loop 4
		cel 2
		scaleSignal $0001
		scaleX 112
		scaleY 112
	)
)
