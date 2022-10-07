;;; Sierra Script 1.0 - (do not remove this comment)
(script# 801)
(include sci.sh)
(use Main)
(use Stair)
(use Kq6Procs)
(use Scaler)
(use Feature)
(use StopWalk)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	cassimaScript 0
)

(instance cassimaScript of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 801)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(cast eachElementDo: #startUpd)
				(addToPics eachElementDo: #dispose)
				(if (not ((ScriptID 80 0) tstFlag: 710 256))
					(theGame givePoints: 1)
				)
				(= cycles 2)
			)
			(1
				(cond 
					((not ((ScriptID 80 0) tstFlag: 710 256)) (messager say: 1 0 7 1 self))
					(((ScriptID 80 0) tstFlag: 711 16384) (messager say: 4 1 21 1 self))
					(else (messager say: 4 1 20 1 self))
				)
			)
			(2
				(ego
					normal: 0
					view: 802
					loop: 0
					cel: 0
					setScale: 0
					scaleX: 128
					scaleY: 128
					posn: 267 165
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(3
				(theIconBar disable:)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 802 10)
				(chinkOverlay addToPic:)
				(background addToPic:)
				(if (not ((ScriptID 80 0) tstFlag: 710 256))
					(theMusic fadeTo: 703 -1)
					(cassima init: stopUpd:)
				)
				(mouseDownHandler addToFront: self)
				(keyDownHandler addToFront: self)
				(= cycles 4)
			)
			(4
				(theIconBar enable:)
				(cond 
					((not ((ScriptID 80 0) tstFlag: 710 256))
						(features add: cassimaFeature)
						(if (OneOf ((inventory at: 39) owner?) 140 210)
							(self
								setScript:
									(sentRing
										start: -1
										next: (endDialog caller: self yourself:)
										yourself:
									)
									0
							)
						else
							(self
								setScript:
									(noRing
										start: -1
										next: (endDialog caller: self yourself:)
										yourself:
									)
									0
							)
						)
						((ScriptID 800 2)
							add: -1 1 0 7 2
							add: convScr
							add: -1 1 0 7 3
							add: -1 1 0 7 4
							add: -1 1 0 7 5
							add: -1 1 0 7 6
							init: script
						)
					)
					(((ScriptID 80 0) tstFlag: 711 16384) (messager say: 4 1 21 2 self oneOnly: 0))
					(else
						((ScriptID 80 0) setFlag: 711 16384)
						(messager say: 4 1 20 2 self oneOnly: 0)
					)
				)
			)
			(5
				(chinkOverlay dispose:)
				(background dispose:)
				(cassArm dispose: delete:)
				(alexArm dispose: delete:)
				(cassimaInset dispose: delete:)
				(alexHead dispose: delete:)
				(cassHead dispose: delete:)
				(if (!= (theMusic number?) 810)
					(theMusic fadeTo: 810 -1)
				)
				(mouseDownHandler delete: self)
				(keyDownHandler delete: self)
				(proc800_1)
				(= cycles 2)
			)
			(6
				(theIconBar enable:)
				(ego setCycle: BegLoop self)
			)
			(7
				(ego posn: 228 157 reset: 0)
				(= cycles 4)
			)
			(8
				(if (not ((ScriptID 80 0) tstFlag: 710 256))
					((ScriptID 80 0) setFlag: 710 256)
					(messager say: 1 0 13 4 self)
				else
					(self cue:)
				)
			)
			(9
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(User input?)
				(not (event modifiers?))
				(== (theIconBar curIcon?) (theIconBar at: 2))
				(or
					(& (event type?) evMOUSEBUTTON)
					(and
						(& (event type?) evKEYBOARD)
						(== (event message?) KEY_RETURN)
					)
				)
				(not (cassimaFeature onMe: event))
			)
			(messager say: 1 1)
			(event claimed: 1)
		)
		(event claimed?)
	)
)

(instance sentRing of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 1 0 8 1 self))
			(1
				(self setScript: cassimaStands self)
			)
			(2
				(self setScript: ringScr self 1)
			)
			(3
				((ScriptID 800 2)
					add: -1 1 0 8 2
					add: -1 1 0 8 3
					init: self
				)
			)
			(4
				(self setScript: ringScr self)
			)
			(5
				((ScriptID 800 2)
					add: -1 1 0 8 4
					add: -1 1 0 8 5
					init: self
				)
			)
			(6 (self dispose:))
		)
	)
)

(instance noRing of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 800 2)
					add: -1 1 0 9 1
					add: -1 1 0 9 2
					init: self
				)
			)
			(1
				(self setScript: cassimaStands self)
			)
			(2
				(self setScript: ringScr self 0)
			)
			(3
				((ScriptID 800 2)
					add: -1 1 0 9 3
					add: -1 1 0 9 4
					init: self
				)
			)
			(4
				(self setScript: ringScr self)
			)
			(5
				((ScriptID 800 2)
					add: -1 1 0 9 5
					add: -1 1 0 9 6
					add: -1 1 0 9 7
					add: -1 1 0 9 8
					init: self
				)
			)
			(6 (messager say: 1 0 9 9 self))
			(7 (self dispose:))
		)
	)
)

(instance endDialog of Script
	(properties)
	
	(method (dispose)
		(Bclr 102)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if ((ScriptID 80 0) tstFlag: 711 16)
					(self setScript: watchedVizInStudy self)
				else
					(self setScript: didn_tWatchVizInStudy self)
				)
			)
			(1
				(messager say: 1 0 12 0 self)
			)
			(2
				(theGame handsOn:)
				(theIconBar disable: 0 1 3)
				(if (not (theIconBar curInvIcon?))
					(theIconBar disable: 4)
				)
				(= seconds 11)
			)
			(3
				(theGame handsOff:)
				(features delete: cassimaFeature)
				(cassHead
					view: 7833
					loop: 5
					cel: 0
					posn: 133 79
					show:
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(4 (= cycles 30))
			(5
				(cassHead cycleSpeed: 6 setCycle: BegLoop self)
			)
			(6
				(cassHead hide:)
				(= cycles 2)
			)
			(7
				(messager say: 1 0 13 1 self)
			)
			(8
				(messager say: 1 0 13 2 self)
			)
			(9
				(messager say: 1 0 13 3 self)
			)
			(10 (self dispose:))
		)
	)
)

(instance watchedVizInStudy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 800 2)
					add: -1 1 0 10 1
					add: -1 1 0 10 2
					add: -1 1 0 10 3
					add: -1 1 0 10 4
					add: -1 1 0 10 5
					add: -1 1 0 10 6
					add: -1 1 0 10 7
					init: self
				)
			)
			(1
				(self setScript: (ringScr start: 8 yourself:) self)
			)
			(2
				(messager say: 1 0 10 8 self)
			)
			(3
				(self setScript: ringScr self)
			)
			(4 (self dispose:))
		)
	)
)

(instance didn_tWatchVizInStudy of Script
	(properties
		name "didn'tWatchVizInStudy"
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 800 2)
					add: -1 1 0 11 1
					add: -1 1 0 11 2
					add: -1 1 0 11 3
					init: self
				)
			)
			(1
				(self setScript: (ringScr start: 5 yourself:) self)
			)
			(2
				(messager say: 1 0 11 4 self)
			)
			(3
				(self setScript: ringScr self)
			)
			(4
				(messager say: 1 0 11 5 self oneOnly: 0)
			)
			(5
				(self setScript: ringScr self)
			)
			(6 (self dispose:))
		)
	)
)

(instance giveLetter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(client seconds: 0)
				(alexArm
					view: 7832
					loop: 5
					cel: 0
					posn: 173 83
					show:
					setCycle: CycleTo 1 1 self
				)
			)
			(1
				(messager say: 5 61 22 1 self)
			)
			(2
				(alexArm cel: 2)
				(cassArm
					view: 7832
					loop: 4
					cel: 0
					posn: 113 86
					setCycle: CycleTo 2 1 self
				)
			)
			(3
				(cassArm setCycle: EndLoop self)
				(alexArm setCycle: EndLoop)
			)
			(4
				(cassHead
					init:
					show:
					view: 7832
					setPri: 15
					loop: 7
					cel: 0
					x: 137
					y: 59
				)
				(= seconds 5)
			)
			(5
				(cassHead view: 7832 loop: 8 cel: 0 x: 136 y: 60)
				(= cycles 40)
			)
			(6
				(cassHead hide:)
				(= cycles 2)
			)
			(7
				(messager say: 5 61 22 2 self)
			)
			(8
				(cassArm setCycle: CycleTo 2 -1 self)
				(alexArm setCycle: CycleTo 2 -1 self)
			)
			(9 0)
			(10
				(alexArm setCycle: BegLoop self)
				(cassArm setCycle: BegLoop self)
			)
			(11 0)
			(12
				(alexArm hide:)
				(cassArm loop: 1 cel: 0 stopUpd:)
				(theGame handsOn:)
				(theIconBar disable: 0 1 3)
				(if (not (theIconBar curInvIcon?))
					(theIconBar disable: 4)
				)
				(client seconds: 8)
				(self dispose:)
			)
		)
	)
)

(instance giveDagger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 8 870)
				(theGame givePoints: 3)
				(theGame handsOff:)
				(client seconds: 0)
				(alexArm
					view: 7832
					loop: 3
					cel: 0
					x: 172
					y: 84
					show:
					setCycle: CycleTo 1 1 self
				)
			)
			(1 (messager say: 5 8 0 1 self))
			(2
				(alexArm cel: 2)
				(cassArm
					view: 7832
					loop: 2
					cel: 0
					x: 113
					y: 86
					setCycle: CycleTo 2 1 self
				)
			)
			(3
				(cassArm cel: 3)
				(alexArm setCycle: EndLoop self)
			)
			(4
				(alexArm hide:)
				(= cycles 2)
			)
			(5 (messager say: 5 8 0 2 self))
			(6 (cassArm setCycle: EndLoop self))
			(7
				(cassArm loop: 1 cel: 0 stopUpd:)
				(theGame handsOn:)
				(theIconBar disable: 0 1 3)
				(if (not (theIconBar curInvIcon?))
					(theIconBar disable: 4)
				)
				(client seconds: 8)
				(self dispose:)
			)
		)
	)
)

(instance convScr of Script
	(properties)
	
	(method (dispose)
		(= start (+ state 1))
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cassima cycleSpeed: 8 setCycle: EndLoop self)
			)
			(1 (self dispose:))
		)
	)
)

(instance cassimaStands of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cassima
					setLoop: 1
					cel: 0
					posn: 140 108
					setPri: 13
					setCycle: EndLoop self
				)
			)
			(1
				(cassima
					setLoop: -1
					setScale: Scaler 150 100 171 108
					view: 784
					setCycle: StopWalk -1
				)
				(self dispose:)
			)
		)
	)
)

(instance ringScr of Script
	(properties)
	
	(method (dispose)
		(= start (+ state 1))
		(= register 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cassima setMotion: MoveTo 140 146 self)
			)
			(1
				(if (not register) (self dispose:) else (= cycles 1))
			)
			(2
				(cassima setMotion: MoveTo 136 185 self)
			)
			(3
				(cassimaInset addToPic:)
				(cassima dispose:)
				(cassArm init: stopUpd:)
				(alexArm init: hide:)
				(Bset 102)
				(= cycles 4)
			)
			(4
				(UnLoad 128 783)
				(UnLoad 128 784)
				(self dispose:)
			)
			(5
				(cassHead init: view: 7833 loop: 1 cel: 0)
				(= seconds 3)
			)
			(6
				(cassHead hide: view: 7832)
				(= cycles 3)
			)
			(7 (self dispose:))
			(8
				(alexArm
					view: 7833
					loop: 0
					cel: 0
					posn: 172 84
					show:
					cycleSpeed: 0
					setCycle: EndLoop self
				)
			)
			(9 (self dispose:))
			(10
				(alexArm cel: 1 cycleSpeed: 8 setCycle: BegLoop self)
			)
			(11
				(alexArm view: 7832 hide:)
				(= cycles 2)
			)
			(12
				(= state 0)
				(self dispose:)
			)
		)
	)
)

(instance chinkOverlay of View
	(properties
		x 251
		y 118
		view 7801
		priority 14
		signal $4010
	)
)

(instance background of View
	(properties
		x 175
		y 82
		view 780
		signal $6010
	)
)

(instance cassima of Actor
	(properties
		x 155
		y 103
		view 783
		signal $6000
		illegalBits $0000
	)
)

(instance cassimaInset of View
	(properties
		x 161
		y 190
		z 101
		sightAngle 180
		view 7832
		priority 14
		signal $0010
	)
	
	(method (doVerb theVerb)
		(if
			(and
				(== (curRoom script?) endDialog)
				(< (endDialog seconds?) 2)
			)
			(endDialog seconds: 4)
		)
		(switch theVerb
			(8
				(endDialog setScript: giveDagger)
			)
			(61
				(if (not ((ScriptID 80 0) tstFlag: 710 8))
					((ScriptID 80 0) setFlag: 710 8)
					(endDialog setScript: giveLetter)
				else
					(messager say: 5 theVerb 28)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cassArm of Prop
	(properties
		x 113
		y 86
		view 7832
		loop 1
		priority 15
		signal $4010
		cycleSpeed 8
	)
)

(instance alexArm of Prop
	(properties
		x 113
		y 86
		view 7832
		loop 3
		priority 15
		signal $4010
		cycleSpeed 8
	)
)

(instance alexHead of View
	(properties
		x 194
		y 68
		view 7832
		loop 6
		priority 15
		signal $0010
	)
)

(instance cassHead of Prop
	(properties
		x 137
		y 59
		view 7832
		loop 7
		priority 15
		signal $4010
	)
)

(instance cassimaFeature of Feature
	(properties
		x 121
		y 191
		noun 5
		nsTop 46
		nsLeft 92
		nsBottom 135
		nsRight 151
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 8 61)
			(cassimaInset doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)
