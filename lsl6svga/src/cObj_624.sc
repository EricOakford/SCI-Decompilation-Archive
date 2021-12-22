;;; Sierra Script 1.0 - (do not remove this comment)
(script# 624)
(include sci.sh)
(use Main)
(use fileScr)
(use rm740)
(use Array)
(use PolyPath)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	showCardScr 0
	finishPlumberScr 1
)

(local
	theGGButtonBarCurIcon =  -1
	local1
)
(class cObj of Obj
	(properties
		scratch 0
	)
)

(instance exitCue of cObj
	(properties)
	
	(method (cue)
		(= local1 1)
	)
)

(instance showCardScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(gGraphMenuBar state: (| (gGraphMenuBar state?) $0004))
				(switch global207
					(1
						(= theGGButtonBarCurIcon (ScriptID 0 4))
					)
					(4
						(= theGGButtonBarCurIcon (ScriptID 0 5))
					)
					(5
						(= theGGButtonBarCurIcon (ScriptID 0 6))
					)
					(2
						(= theGGButtonBarCurIcon (ScriptID 0 7))
					)
				)
				(switch register
					(1 (theGame changeScore: 2 237))
					(2 (theGame changeScore: 2 238))
					(3 (theGame changeScore: 2 236))
				)
				(if (OneOf register 3 2)
					(ego view: 901 setLoop: 1 cel: 0 setCycle: CT 3 1 self)
				else
					(= cycles 2)
				)
			)
			(1 (= cycles 2))
			(2
				(switch register
					(1 (messager say: 4 0 0 1 self))
					(2 (messager say: 5 0 0 1 self))
					(3 (messager say: 3 0 0 1 self))
				)
			)
			(3 (= ticks 30))
			(4
				(cast eachElementDo: #hide)
				(switch register
					(3 (thePlane drawPic: 96 9))
					(1 (thePlane drawPic: 97 9))
					(2 (thePlane drawPic: 95 9))
				)
				(Bset 86)
				(theIconBar
					enableIcon: ((ScriptID 0 11) init: exitCue yourself:)
				)
				(theGame setCursor: normalCursor)
				(= state (+ state register))
				(= ticks 5)
			)
			(5)
			(6
				(= temp0 (ByteArray new: 500))
				(Message 0 620 4 0 0 2 (temp0 data?))
				(proc79_4 (temp0 data?) 7 -1 70 30 250 4115)
				(temp0 dispose:)
				(= state (+ state 2))
				(= cycles 2)
			)
			(7
				(= temp0 (ByteArray new: 500))
				(Message 0 620 5 0 0 2 (temp0 data?))
				(proc79_4 (temp0 data?) 1 -1 19 21 305 4115)
				(temp0 dispose:)
				(++ state)
				(= cycles 2)
			)
			(8
				(= temp0 (ByteArray new: 500))
				(Message 0 620 3 0 0 2 (temp0 data?))
				(proc79_4 (temp0 data?) 1 -1 56 41 270 4115)
				(temp0 dispose:)
				(= cycles 2)
			)
			(9
				(if (not local1)
					(-- state)
				else
					(proc79_5)
					((ScriptID 0 11) dispose:)
					(= local1 0)
				)
				(= cycles 2)
			)
			(10
				(Bclr 86)
				(thePlane drawPic: 620 10)
				(cast eachElementDo: #show)
				(if (OneOf register 3 2)
					(ego setCycle: Beg self)
				else
					(= cycles 2)
				)
			)
			(11
				(if (OneOf register 3 2)
					(ego normalize: 900 1)
				else
					(ego normalize: 900 2)
				)
				(UnLoad 129 95)
				(UnLoad 129 96)
				(UnLoad 129 97)
				(UnLoad 129 98)
				(= gGButtonBarCurIcon theGGButtonBarCurIcon)
				(gGraphMenuBar state: (& (gGraphMenuBar state?) $fffb))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance finishPlumberScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 231 113 self)
			)
			(1 (ego setHeading: 90 self))
			(2 (= cycles 2))
			(3
				(markus
					view: 619
					setLoop: 1
					x: 280
					y: 93
					setPri: 30
					init:
				)
				(sfx number: 32 loop: 1 play:)
				(= ticks 60)
			)
			(4
				((ScriptID 620 4)
					view: 624
					setLoop: 1
					cel: 0
					setCycle: End self
				)
			)
			(5
				(markus
					setPri: 88
					setCycle: Fwd
					setMotion: MoveTo 254 104 self
				)
			)
			(6
				(ego
					view: 324
					setLoop: 1
					cel: 1
					setCycle: 0
					cycleSpeed: 6
				)
				(markus setLoop: 2 cel: 1 setCycle: 0)
				(= ticks 30)
			)
			(7
				(markus cel: 0)
				(= ticks 30)
			)
			(8
				(sfx number: 0 dispose:)
				((ScriptID 620 4) setCycle: Beg self)
			)
			(9
				((ScriptID 620 4) view: 620 setLoop: 0 cel: 0)
				(sfx number: 33 loop: 1 play:)
				(= ticks 60)
			)
			(10
				(sfx number: 0 dispose:)
				(= ticks 30)
			)
			(11
				(if (and (Btst 34) (not (Btst 12)))
					(messager say: 0 0 10 0 self)
				else
					(messager say: 0 0 11 0 self)
				)
			)
			(12
				(markus
					setLoop: 0
					setCycle: Fwd
					setMotion: MoveTo 258 120 self
				)
			)
			(13
				(ego view: 900 setLoop: 8 cel: 0)
				(markus
					setLoop: 0
					setCycle: Fwd
					setMotion: MoveTo 261 132 self
				)
			)
			(14
				(ego view: 324 setLoop: 1 cel: 2)
				(markus
					setLoop: 0
					setCycle: Fwd
					setMotion: MoveTo 278 205 self
				)
			)
			(15
				(if global205 (proc79_7))
				(markus dispose:)
				(= cycles 2)
			)
			(16
				(if (and (Btst 34) (not (Btst 12))) (Bset 12))
				(if (and (Btst 80) (Btst 79)) (Bclr 79) (Bclr 80))
				(Bclr 54)
				(Bclr 52)
				(Bclr 50)
				(ego normalize: 900 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance markus of Actor
	(properties
		noun 14
		sightAngle 10
		x 265
		y 85
		view 620
	)
)

(instance sfx of Sound
	(properties)
)
