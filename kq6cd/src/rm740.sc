;;; Sierra Script 1.0 - (do not remove this comment)
(script# 740)
(include sci.sh)
(use Main)
(use CastleRoom)
(use Kq6Procs)
(use Print)
(use Conv)
(use Scaler)
(use Polygon)
(use Feature)
(use StopWalk)
(use Timer)
(use Motion)
(use Actor)
(use System)

(public
	rm740 0
	priest 1
	vizier 2
	genie 3
	jollo 4
	saladin 5
	roomConv 7
	door 8
	guard3 9
	proc740_10 10
	caliphimallaria 11
	grahamvalanice 12
	rosella 13
	prince 14
	beauty 15
	redQueen 16
	whiteQueen 17
	druid1 18
	druid2 19
	druid3 20
	leftGuard 21
	rightGuard 22
	theGreatEscape 23
)

(local
	local0 =  1
	local1
	local2
	local3
	local4
	local5
)
(procedure (proc740_10)
	(if (!= (curRoom curPic?) 740)
		(theIconBar disable:)
		(curRoom drawPic: 740 10)
	)
	(priest
		view: 748
		init:
		setLoop: 0
		posn: 178 139
		setCycle: 0
		cel: 0
		setScale: 0
		signal: 26624
	)
	(saladin
		view: 7411
		init:
		setLoop: 0
		cel: 0
		posn: 183 129
		signal: 26624
		addToPic:
	)
	(if (Btst 15)
		(caliphimallaria init: stopUpd:)
		(= global164 105)
		(= global165 155)
	else
		(= global164 116)
		(= global165 143)
	)
	(if (== ((inventory at: 25) owner?) curRoomNum)
		(genie
			view: 7020
			init:
			setLoop: 0
			cel: 0
			posn: 274 160
			scaleSignal: 1
			scaleX: 115
			scaleY: 115
			signal: 26624
			stopUpd:
		)
		(grahamvalanice init: stopUpd:)
		(rosella init: stopUpd:)
		(if ((ScriptID 80 0) tstFlag: 710 16)
			(druid1 init: stopUpd:)
			(druid2 init: stopUpd:)
			(druid3 init: stopUpd:)
			(celeste init: addToPic:)
			(aeriel init: addToPic:)
			(azure init: addToPic:)
			(beauty init: setPri: 15 stopUpd:)
			(prince init: setPri: 14 stopUpd:)
			(redQBody init: addToPic:)
			(whiteQBody init: addToPic:)
			(redQueen init: stopUpd:)
			(whiteQueen init: stopUpd:)
		)
	)
	(leftGuard init: stopUpd:)
	(rightGuard init: stopUpd:)
	(jollo
		init:
		view: 7463
		loop: 1
		cel: 0
		posn: 116 143
		scaleSignal: 1
		scaleX: 115
		scaleY: 115
		posn: global164 global165
		signal: 26624
		stopUpd:
	)
	(door init: addToPic:)
	(theIconBar enable:)
)

(instance rm740 of CastleRoom
	(properties
		noun 3
		picture 740
		style $000a
		vanishingX 204
		vanishingY 99
		autoLoad 0
		minScaleSize 82
		maxScaleSize 108
		minScaleY 142
		maxScaleY 189
	)
	
	(method (init &tmp temp0 temp1)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init: 0 -10 319 -10 319 189 228 121 221 121 221 134 120 134 0 179
					yourself:
				)
		)
		(features add: roomFeatures eachElementDo: #init)
		(if
			(and
				(== prevRoomNum 99)
				debugging
				(FileIO fiEXISTS {g})
			)
			(switch
				(Print
					addText: {select:}
					addButton: 1 {V wedding} 0 12
					addButton: 2 {A wedding} 0 26
					init:
				)
				(1
					(if
						(Print
							addText: {Are Cassima's parents alive?}
							addButton: 1 {No} 0 12
							addButton: 0 {Yes} 0 26
							init:
						)
						((ScriptID 80 0) setFlag: 709 128)
					)
					(theMusic number: 701 setLoop: -1 play:)
					((ScriptID 80 0) setFlag: 709 2)
					(ego get: 24 get: 31)
					((inventory at: 25) owner: 750)
					((inventory at: 8) owner: 870)
					(= prevRoomNum 730)
				)
				(2 (= prevRoomNum 180))
			)
		)
		(if (== prevRoomNum 180)
			(if
				(and
					debugging
					(FileIO fiEXISTS {g})
					(not (theMusic handle?))
				)
				(theMusic number: 743 setLoop: -1 play:)
			)
			(if
				(and
					debugging
					(FileIO fiEXISTS {g})
					(Print
						addText: {Are Cassima's parents alive?}
						addButton: 0 {No} 0 12
						addButton: 1 {Yes} 0 26
						init:
					)
				)
				(Bset 15)
			)
			(if
				(and
					debugging
					(FileIO fiEXISTS {g})
					(Print
						addText: {genie status?}
						addButton: 0 {Killed with peppermint} 0 12
						addButton: 1 {Saved} 0 26
						init:
					)
				)
				((inventory at: 25) owner: curRoomNum)
			)
			(if
				(and
					debugging
					(FileIO fiEXISTS {g})
					(Print
						addText: {Found missing treasure?}
						addButton: 0 {No} 0 12
						addButton: 1 {Yes} 0 26
						init:
					)
				)
				((ScriptID 80 0) setFlag: 710 16)
			)
			(if
				(and
					debugging
					(FileIO fiEXISTS {g})
					(Print
						addText: {Befriended Court Clown?}
						addButton: 0 {No} 0 12
						addButton: 1 {Yes} 0 26
						init:
					)
				)
				(Bset 10)
			)
			(if (and debugging (FileIO fiEXISTS {g}))
				(repeat
					(switch
						(= temp1
							(Print
								addText: {Which ring?}
								addButton: 1 {Cassima has a ring} 0 12
								addButton: 2 {Alex has a ring_} 0 26
								addButton: 3 {Left ring at pawn shop} 0 40
								init:
							)
						)
						(1
							((inventory at: 39) owner: 140)
						)
						(2 (ego get: 39))
					)
					(if temp1 (break))
				)
			)
			(super init: &rest)
			(proc740_10)
			(self setScript: (ScriptID 743 0))
		else
			(super init: &rest)
			(ego
				init:
				reset: 3
				setScale: Scaler maxScaleSize minScaleSize maxScaleY minScaleY
			)
			(door init:)
			(switch prevRoomNum
				(790 (ego posn: 222 126))
				(else 
					(if ((ScriptID 80 0) tstFlag: 709 2)
						(ego posn: 131 180)
						(saladin
							posn: 147 187
							init:
							setStep: 5 3
							setCycle: StopWalk -1
							setScale: Scaler 110 93 189 141
						)
						(if (not (Btst 10)) (jollo init: stopUpd:))
						((ScriptID 80 5) actions: guardsActions)
						((ScriptID 80 6) actions: guardsActions)
						(guard3 actions: guardsActions)
						(self setScript: (ScriptID 742 0))
					else
						(ego posn: 128 185)
						(self setScript: (ScriptID 741 0))
					)
				)
			)
			((ego scaler?) doit:)
		)
		(if (not script) (theGame handsOn:))
	)
	
	(method (doit &tmp temp0)
		(= temp0 (ego onControl: 1))
		(cond 
			(script 0)
			((& temp0 $4000)
				(if (cast contains: genie) (genie setScript: 0))
				(curRoom newRoom: 790)
			)
		)
		(if (ego scaler?)
			(cond 
				((< (ego y?) 142)
					(if local0
						(= local0 0)
						(ego setScale: Scaler 83 43 142 120)
					)
				)
				((not local0)
					(= local0 1)
					(ego
						setScale: Scaler maxScaleSize minScaleSize maxScaleY minScaleY
					)
				)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 741)
		(DisposeScript 742)
		(DisposeScript 743)
		(DisposeScript 964)
		(DisposeScript 939)
		(DisposeScript 752)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (theIconBar curIcon?) (theIconBar at: 3))
				(!= (event type?) evVERB)
				(not (event modifiers?))
				(not (vizier onMe: event))
				(not (jollo onMe: event))
				(or
					(and
						(& (event type?) evKEYBOARD)
						(== (event message?) KEY_RETURN)
					)
					(& (event type?) evMOUSEBUTTON)
				)
			)
			(theGame handsOff:)
			((ScriptID 740 7) add: -1 3 2 21 0)
			(curRoom setScript: (ScriptID 742 1))
			(event claimed: 1)
			(event claimed?)
			(return)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(= temp0
			(if ((ScriptID 80 0) tstFlag: 710 2048) 22 else 21)
		)
		(switch theVerb
			(1
				(messager say: noun theVerb temp0)
				1
			)
			(2
				((ScriptID 80 0) setFlag: 710 2048)
				(messager say: noun theVerb temp0)
				1
			)
			(else 
				(super doVerb: theVerb)
				1
			)
		)
	)
	
	(method (cue)
		(if (== msgType 2)
			(curRoom setScript: (ScriptID 52))
		else
			(curRoom newRoom: 94)
		)
	)
	
	(method (newRoom n)
		(bTimer dispose: delete:)
		(pTimer dispose: delete:)
		(lgTimer dispose: delete:)
		(rgTimer dispose: delete:)
		(super newRoom: n)
	)
)

(instance roomFeatures of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(= y 1)
		(= sightAngle 26505)
	)
	
	(method (onMe event &tmp temp0)
		(return
			(if (== (curRoom curPic?) 740)
				(return
					(cond 
						(
							(or
								(and
									(&
										(= temp0 (OnControl 4 (event x?) (event y?)))
										$0002
									)
									(= noun 17)
								)
								(and (& temp0 $0004) (= noun 18))
								(and (& temp0 $0008) (= noun 9))
							)
						)
						((& temp0 $0010) (= noun 15))
					)
				)
			else
				(return 0)
			)
		)
	)
)

(instance priest of Prop
	(properties
		x 179
		y 128
		view 741
		cel 1
		signal $5000
		cycleSpeed 8
		detailLevel 2
	)
)

(instance vizier of Prop
	(properties
		x 176
		y 142
		view 741
		loop 2
		cel 2
		signal $5000
		cycleSpeed 8
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 5 2)
			(roomConv add: -1 noun theVerb 0 1)
			(curRoom setScript: (ScriptID 742 1))
		else
			(super doVerb: theVerb &rest)
		)
	)
	
	(method (onMe event)
		(if (super onMe: event)
			(if
				(and
					(== view 741)
					(== loop 2)
					(< (- (event x?) nsLeft) 15)
					(= noun 6)
				)
			else
				(= noun 4)
			)
		)
	)
)

(instance genie of Prop
	(properties
		x 176
		y 142
		view 741
		loop 3
		cel 5
		signal $4000
		cycleSpeed 8
		detailLevel 2
	)
)

(instance saladin of Actor
	(properties
		x 176
		y 142
		noun 7
		view 736
		loop 3
		cel 5
		signal $4000
		cycleSpeed 8
		detailLevel 2
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if (== (approachCode doit: theVerb) -32768)
			(= theVerb 0)
		)
		(= temp0
			(if ((ScriptID 80 0) tstFlag: 710 2048) 22 else 21)
		)
		(messager say: noun theVerb temp0)
	)
	
	(method (cue)
		(self setHeading: 0)
	)
)

(instance jollo of Prop
	(properties
		x 61
		y 167
		view 746
		cel 1
		signal $5000
		cycleSpeed 8
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 19 1 0 1))
			(2
				(if ((ScriptID 80 0) tstFlag: 710 2048)
					(messager say: 19 2 22 1)
				else
					(messager say: 19 2 21 1)
				)
			)
			(5
				(if ((ScriptID 80 0) tstFlag: 710 2048)
					(messager say: 19 5 22 1)
				else
					(messager say: 19 5 21 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(self view: 7464 loop: 0 cel: 0 stopUpd:)
		(super cue:)
	)
)

(instance door of Prop
	(properties
		x 220
		y 108
		noun 8
		approachX 220
		approachY 108
		view 740
		priority 8
		signal $4011
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if ((ScriptID 80 0) tstFlag: 710 2048)
					0
				else
					(messager say: noun theVerb 21)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance roomConv of Conversation
	(properties)
)

(instance prince of Prop
	(properties
		x 255
		y 180
		view 7020
		loop 1
		priority 15
		signal $6810
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(switch (++ local1)
			(1 (pTimer setReal: self 5))
			(2 (self setCycle: Beg self))
			(3 (self addToPic:))
		)
	)
)

(instance beauty of Prop
	(properties
		x 242
		y 188
		view 7020
		loop 2
		priority 14
		signal $6810
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(switch (++ local2)
			(1 (bTimer setCycle: self 5))
			(2
				(self cel: 2)
				(bTimer setCycle: self 5)
			)
			(3
				(self cel: 3)
				(bTimer setCycle: self 5)
			)
			(4 (self setCycle: Beg self))
			(5 (bTimer setReal: self 3))
			(6
				(= local2 0)
				(self setCycle: End self)
			)
		)
	)
)

(instance azure of View
	(properties
		x 20
		y 185
		view 747
		loop 2
		signal $6800
	)
)

(instance aeriel of View
	(properties
		x 31
		y 176
		view 747
		loop 2
		cel 1
		signal $6800
	)
)

(instance celeste of View
	(properties
		x 55
		y 170
		view 747
		loop 2
		cel 2
		signal $6800
	)
)

(instance druid1 of Prop
	(properties
		x 94
		y 189
		view 747
		loop 3
		signal $6800
		detailLevel 2
	)
)

(instance druid2 of Prop
	(properties
		x 69
		y 189
		view 747
		loop 3
		cel 1
		signal $6800
		detailLevel 2
	)
)

(instance druid3 of Prop
	(properties
		x 120
		y 179
		view 747
		loop 3
		cel 2
		signal $6800
		detailLevel 2
	)
)

(instance caliphimallaria of Prop
	(properties
		x 128
		y 147
		view 7450
		loop 2
		signal $6800
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(switch (++ local5)
			(1 (self setCycle: Beg self))
			(2
				(cTimer setReal: self (Random 2 4))
			)
			(3
				(= local5 0)
				(self setCycle: End self)
			)
		)
	)
)

(instance grahamvalanice of Prop
	(properties
		x 216
		y 144
		view 745
		loop 4
		cel 1
		signal $6800
		detailLevel 2
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance rosella of Prop
	(properties
		x 235
		y 145
		view 745
		loop 4
		cel 2
		signal $6800
		detailLevel 2
	)
)

(instance leftGuard of Prop
	(properties
		x 148
		y 189
		view 7473
		loop 1
		signal $6800
	)
	
	(method (cue param1)
		(super cue:)
		(if (== param1 99) (= local4 param1))
		(= param1 0)
		(switch (++ local4)
			(1
				(lgTimer setReal: self (Random 1 2))
			)
			(2
				(self cel: 2)
				(lgTimer setReal: self (Random 1 2))
			)
			(3
				(self cel: 3)
				(lgTimer setReal: self (Random 1 2))
			)
			(4 (self setCycle: Beg self))
			(5
				(lgTimer setReal: self (Random 2 3))
			)
			(6
				(= local4 0)
				(self setCycle: End self)
			)
			(100
				(= local4 0)
				(lgTimer dispose:)
				(self cel: 0 setCycle: 0 stopUpd:)
			)
		)
	)
)

(instance rightGuard of Prop
	(properties
		x 174
		y 189
		view 7473
		signal $6800
	)
	
	(method (cue param1)
		(super cue:)
		(if (== param1 99) (= local3 param1))
		(= param1 0)
		(switch (++ local3)
			(1
				(rgTimer setReal: self (Random 1 2))
			)
			(2
				(self cel: 1)
				(rgTimer setReal: self (Random 1 2))
			)
			(3
				(self cel: 2)
				(rgTimer setReal: self (Random 1 2))
			)
			(4 (self setCycle: Beg self))
			(5
				(rgTimer setReal: self (Random 2 3))
			)
			(6
				(= local3 0)
				(self setCycle: End self)
			)
			(100
				(= local3 0)
				(rgTimer dispose:)
				(self cel: 0 setCycle: 0 stopUpd:)
			)
		)
	)
)

(instance redQueen of Prop
	(properties
		x 287
		y 172
		view 7471
		priority 15
		signal $6810
		detailLevel 2
	)
)

(instance redQBody of View
	(properties
		x 286
		y 181
		view 747
		signal $6800
	)
)

(instance whiteQBody of View
	(properties
		x 296
		y 175
		view 747
		cel 1
		signal $6800
	)
)

(instance whiteQueen of Prop
	(properties
		x 296
		y 159
		view 7471
		loop 1
		priority 14
		signal $6810
		detailLevel 2
	)
)

(instance guard3 of Actor
	(properties)
)

(instance guardsActions of Actions
	(properties)
	
	(method (doVerb theVerb &tmp temp0 temp1 temp2)
		(= temp0 1)
		(= temp1
			(if ((ScriptID 80 0) tstFlag: 710 2048) 22 else 21)
		)
		(= temp2
			(if (== ((CueObj client?) modNum?) -1)
				curRoomNum
			else
				((CueObj client?) modNum?)
			)
		)
		(if
			(Message
				msgGET
				temp2
				((CueObj client?) noun?)
				theVerb
				temp1
				1
			)
			(messager say: ((CueObj client?) noun?) theVerb temp1)
		else
			(= temp0 0)
		)
		(return temp0)
	)
)

(instance bTimer of Timer
	(properties)
)

(instance pTimer of Timer
	(properties)
)

(instance lgTimer of Timer
	(properties)
)

(instance rgTimer of Timer
	(properties)
)

(instance cTimer of Timer
	(properties)
)

(instance theGreatEscape of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable: 6)
				(theMusic number: 742 loop: -1 play:)
				(vizier cycleSpeed: 6 setCycle: CT 9 1 self)
			)
			(1
				(door hide:)
				(door approachVerbs: 5)
				(vizier setCycle: End self)
			)
			(2
				(door show: cel: 1 stopUpd:)
				(vizier dispose:)
				(= cycles 3)
			)
			(3
				(UnLoad 128 7413)
				(theIconBar enable: 6)
				(self dispose:)
			)
		)
	)
)
