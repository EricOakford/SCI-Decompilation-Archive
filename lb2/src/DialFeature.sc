;;; Sierra Script 1.0 - (do not remove this comment)
(script# 561)
(include sci.sh)
(use Main)
(use Inset)
(use PolyPath)
(use Feature)
(use StopWalk)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	safePicture 0
	safeDoor 1
)

(local
	local0
	local1
	local2
	local3
	dialNumberCel
	[theDialNumberCel 4]
)
(instance safePicture of Prop
	(properties
		x 82
		y 100
		noun 38
		modNum 561
		approachX 93
		approachY 149
		view 564
		loop 1
		priority 6
		signal $0010
	)
	
	(method (init)
		(super init: &rest)
		(safe init: stopUpd:)
		(safeDoor init: stopUpd:)
		(self doVerb: 4)
	)
	
	(method (dispose)
		(sFX dispose:)
		(safe dispose:)
		(safeDoor dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((== (self cel?) 0)
						(sFX number: 44 flags: 1 setLoop: 1 play:)
						(self setCycle: End)
					)
					((== (safeDoor cel?) 0)
						(sFX number: 45 flags: 1 setLoop: 1 play:)
						(self setCycle: Beg)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance safeDoor of Prop
	(properties
		x 76
		y 95
		noun 40
		modNum 561
		approachX 93
		approachY 149
		view 564
		loop 2
		priority 5
		signal $0010
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (curRoom setInset: inDial))
			(8 (curRoom setInset: inDial))
			(4
				(ego setHeading: 270)
				(++ local3)
				(cond 
					(local0
						(if (== (self cel?) 0)
							(= local3 0)
							(sFX number: 560 flags: 1 setLoop: 1 play:)
							(self setPri: 7 setCycle: End)
							(= local1 1)
						else
							(= local0 0)
							(self setScript: sCloseSafe)
						)
					)
					((< local3 3) (curRoom setInset: inDial))
					(else (self setScript: sHeimlichEnters))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inDial of Inset
	(properties
		view 564
		loop 4
		x 53
		y 33
		disposeNotOnMe 1
		modNum 561
	)
	
	(method (init)
		(super init: &rest)
		(dialNumber init:)
		(zero init:)
		(one init:)
		(two init:)
		(three init:)
		(four init:)
		(five init:)
		(six init:)
		(seven init:)
		(eight init:)
		(nine init:)
		(safeDoor stopUpd:)
		(safePicture stopUpd:)
		(ego stopUpd:)
		(= local2 0)
		(= [theDialNumberCel 0] -1)
		(= [theDialNumberCel 1] -1)
		(= [theDialNumberCel 2] -1)
		(= [theDialNumberCel 3] -1)
	)
	
	(method (dispose)
		(dialNumber dispose:)
		(zero dispose:)
		(one dispose:)
		(two dispose:)
		(three dispose:)
		(four dispose:)
		(five dispose:)
		(six dispose:)
		(seven dispose:)
		(eight dispose:)
		(nine dispose:)
		(safeDoor startUpd:)
		(safePicture startUpd:)
		(ego startUpd:)
		(super dispose:)
	)
)

(instance dialNumber of Prop
	(properties
		x 61
		y 39
		noun 78
		modNum 561
		view 564
		loop 5
		priority 15
		signal $0010
		cycleSpeed 18
	)
	
	(method (initialize)
	)
)

(instance safe of View
	(properties
		x 55
		y 70
		noun 39
		modNum 561
		approachX 93
		approachY 149
		view 564
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if local1
					(curRoom setInset: inDiary)
				else
					(messager say: 39 1 0 0 0 561)
				)
			)
			(8
				(if local1
					(curRoom setInset: inDiary)
				else
					(messager say: 39 1 0 0 0 561)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inDiary of Inset
	(properties
		view 564
		loop 3
		x 53
		y 45
		disposeNotOnMe 1
		modNum 561
		noun 60
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(theGame points: 1 174)
				((ScriptID 21 0) doit: 1030)
				(messager say: 60 1 0 0 0 561)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sTurnTumbler of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= temp0 (if (mod local2 2) -1 else 1))
				(sFX number: 562 flags: 1 play: setLoop: -1)
				(dialNumber
					setCycle: CT (mod (+ dialNumberCel register) 10) temp0 self
				)
			)
			(1
				(= dialNumberCel (dialNumber cel?))
				(if (< (++ local2) 5)
					(= [theDialNumberCel (- local2 1)] dialNumberCel)
				)
				(if (== local2 4)
					(if
						(and
							(== [theDialNumberCel 0] 0)
							(== [theDialNumberCel 1] 5)
							(== [theDialNumberCel 2] 2)
							(== [theDialNumberCel 3] 7)
						)
						(= local0 1)
						(sFX number: 558 flags: 1 setLoop: 1 play:)
					else
						(= local0 0)
						(sFX stop:)
					)
					(inDial dispose:)
					(dialNumber dispose:)
				else
					(sFX stop:)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCloseSafe of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(safeDoor setPri: 5 setCycle: Beg self)
			)
			(1
				(sFX number: 561 flags: 1 play: setLoop: 1)
				(= local1 0)
				(self dispose:)
			)
		)
	)
)

(instance sHeimlichEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic2 fade:)
				((ScriptID 560 4)
					locked: 0
					exitType: 2
					caller: self
					open:
				)
			)
			(1
				(theMusic2 number: 19 loop: -1 flags: 1 play:)
				(theGame handsOff:)
				(= ticks 60)
			)
			(2
				(Face ego (ScriptID 560 4))
				(= ticks 60)
			)
			(3
				((ScriptID 32 0)
					init:
					setLoop: 0
					setCel: 5
					posn: 0 146
					setPri: 10
					room: 560
					view: 814
					setCycle: Walk
					setMotion: MoveTo 11 148 self
				)
			)
			(4
				((ScriptID 32 0) setMotion: PolyPath 64 163 self)
			)
			(5
				(Face ego (ScriptID 32 0))
				((ScriptID 32 0) setPri: -1 setCycle: StopWalk -1)
				(= cycles 1)
			)
			(6
				(Face (ScriptID 32 0) ego)
				(= ticks 60)
			)
			(7
				(messager say: 3 0 87 0 self 1889)
			)
			(8
				(ego setMotion: PolyPath 10 175 self)
			)
			(9
				(Face (ScriptID 32 0) ego)
				(ego setMotion: PolyPath 4 145 self)
			)
			(10
				(Bset 97)
				(theMusic2 fade: self)
			)
			(11
				(theGame handsOn:)
				(curRoom newRoom: 550)
				(self dispose:)
			)
		)
	)
)

(class DialFeature of Feature
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
		dialPosn 0
	)
	
	(method (initialize)
	)
)

(instance zero of DialFeature
	(properties
		y 100
		nsTop 38
		nsLeft 97
		nsBottom 50
		nsRight 110
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(dialNumber setScript: sTurnTumbler 0 dialPosn)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance one of DialFeature
	(properties
		y 100
		nsTop 45
		nsLeft 120
		nsBottom 57
		nsRight 135
		dialPosn 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(dialNumber setScript: sTurnTumbler 0 dialPosn)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance two of DialFeature
	(properties
		y 100
		nsTop 63
		nsLeft 133
		nsBottom 75
		nsRight 149
		dialPosn 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(dialNumber setScript: sTurnTumbler 0 dialPosn)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance three of DialFeature
	(properties
		y 100
		nsTop 87
		nsLeft 135
		nsBottom 100
		nsRight 150
		dialPosn 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(dialNumber setScript: sTurnTumbler 0 dialPosn)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance four of DialFeature
	(properties
		y 100
		nsTop 103
		nsLeft 123
		nsBottom 116
		nsRight 135
		dialPosn 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(dialNumber setScript: sTurnTumbler 0 dialPosn)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance five of DialFeature
	(properties
		y 100
		nsTop 109
		nsLeft 97
		nsBottom 123
		nsRight 111
		dialPosn 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(dialNumber setScript: sTurnTumbler 0 dialPosn)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance six of DialFeature
	(properties
		y 100
		nsTop 104
		nsLeft 76
		nsBottom 120
		nsRight 90
		dialPosn 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(dialNumber setScript: sTurnTumbler 0 dialPosn)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance seven of DialFeature
	(properties
		y 100
		nsTop 86
		nsLeft 58
		nsBottom 101
		nsRight 74
		dialPosn 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(dialNumber setScript: sTurnTumbler 0 dialPosn)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance eight of DialFeature
	(properties
		y 100
		nsTop 54
		nsLeft 58
		nsBottom 75
		nsRight 75
		dialPosn 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(dialNumber setScript: sTurnTumbler 0 dialPosn)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance nine of DialFeature
	(properties
		y 100
		nsTop 43
		nsLeft 74
		nsBottom 58
		nsRight 91
		dialPosn 9
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(dialNumber setScript: sTurnTumbler 0 dialPosn)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)
