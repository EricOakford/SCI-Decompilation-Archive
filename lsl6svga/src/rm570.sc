;;; Sierra Script 1.0 - (do not remove this comment)
(script# 570)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use Scaler)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use System)

(public
	rm570 0
)

(local
	local0
)
(instance rm570 of LarryRoom
	(properties
		noun 1
		horizon 0
		autoLoad 0
	)
	
	(method (init)
		(if (== prevRoomNum 700) (= picture 572))
		(super init: &rest)
		(if (!= prevRoomNum 700)
			(ego
				normalize: 570 0
				setCycle: 0
				cel: 0
				setSpeed: 8
				posn: 166 109
				init:
			)
			(ego loop: 0)
			(greenButton init:)
			(redButton init:)
			((ScriptID 0 11) init: self)
			(theGame handsOn:)
			(self setScript: messageScr)
		else
			(Bset 104)
			(self setScript: downShaftScr)
		)
	)
	
	(method (dispose)
		(if (theIconBar contains: (ScriptID 0 11))
			((ScriptID 0 11) dispose:)
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (curRoom script?) ((curRoom script?) dispose:))
		(super doVerb: theVerb)
	)
	
	(method (cue)
		(curRoom newRoom: 560)
	)
)

(instance greenButton of Feature
	(properties
		noun 3
	)
	
	(method (init)
		(super init: &rest)
		(= y 200)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 204 80 217 79 215 88 205 87
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom setScript: buttonScr)
		else
			(if (curRoom script?) ((curRoom script?) dispose:))
			(super doVerb: theVerb)
		)
	)
)

(instance redButton of Feature
	(properties
		noun 4
	)
	
	(method (init)
		(super init: &rest)
		(= y 200)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 215 88 215 98 204 96 205 87
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom setScript: closeScr)
		else
			(if (curRoom script?) ((curRoom script?) dispose:))
			(super doVerb: theVerb)
		)
	)
)

(instance buttonScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego loop: 1 cel: 0 setCycle: End self)
			)
			(1
				(theMusic2 number: 572 loop: 1 play:)
				(= ticks 20)
			)
			(2 (ego setCycle: Beg self))
			(3
				(ego loop: 0 cel: 0)
				(= cycles 2)
			)
			(4 (messager say: 3 4 0 0 self))
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance messageScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1 (= ticks 360))
			(2 (messager say: 0 0 0 0 self))
			(3 (self dispose:))
		)
	)
)

(instance closeScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(ego loop: 2 cel: 0 setCycle: CT 2 1 self)
			)
			(2
				(theMusic2 number: 572 loop: 1 play:)
				(= ticks 20)
			)
			(3
				(sfx number: 575 loop: 1 play:)
				(ego setCycle: End self)
			)
			(4
				(ego view: 571 loop: 0 cel: 0 setCycle: End self)
			)
			(5
				(ego cel: 0 setCycle: End self)
			)
			(6
				(ego loop: 1 cel: 0 setCycle: End self)
			)
			(7
				(theMusic2 play:)
				(= ticks 20)
			)
			(8
				(ego view: 573 loop: 0 cel: 0 setCycle: End self)
				(= ticks 10)
			)
			(9
				(sfx number: 573 loop: 1 play:)
			)
			(10 (= ticks 90))
			(11
				(ego view: 575 loop: 0 cel: 0 setCycle: CT 2 1 self)
			)
			(12
				(theMusic2 play:)
				(= ticks 20)
			)
			(13 (ego setCycle: End self))
			(14
				(if (Random 0 1)
					(self setScript: noseScr self)
				else
					(sfx number: 574 loop: 1 play:)
					(ego loop: 1 cel: 0 setCycle: End self)
				)
			)
			(15 (= ticks 120))
			(16
				(curRoom drawPic: 572)
				(ego
					view: 572
					setLoop: 0
					cel: 0
					posn: 56 85
					scaleSignal: (| (ego scaleSignal?) $0004)
					setScaler: Scaler 100 1 85 3
					setSpeed: 0
				)
				(= cycles 2)
			)
			(17
				(theMusic number: 571 loop: -1 play:)
				(theDoits add: volumeCode)
				(ego
					scaleSignal: (& (ego scaleSignal?) $fffb)
					setMotion: MoveTo 318 3 self
				)
			)
			(18
				(theDoits delete: volumeCode)
				(curRoom newRoom: 700)
			)
		)
	)
)

(instance noseScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sfx number: 574 loop: 1 play:)
				(= cycles 2)
			)
			(1
				(ego view: 576 loop: 0 cel: 0 setCycle: CT 5 1 self)
			)
			(2
				(theMusic2 number: 576 loop: 1 play:)
				(ego setCycle: End self)
			)
			(3 (= ticks 120))
			(4
				(ego loop: 1 cel: 0 setCycle: End self)
			)
			(5
				(theMusic2 number: 710 loop: 1 play:)
				(= cycles 1)
			)
			(6 (self dispose:))
		)
	)
)

(instance downShaftScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					init:
					view: 572
					setLoop: 0
					cel: 0
					posn: 318 3
					scaleSignal: (| (ego scaleSignal?) $0004)
					setScaler: Scaler 100 1 85 3
					setSpeed: 0
				)
				(= cycles 2)
			)
			(1
				(theMusic number: 571 loop: -1 play: 1)
				(theDoits add: volumeCode)
				(ego
					scaleSignal: (& (ego scaleSignal?) $fffb)
					setMotion: MoveTo 56 85 self
				)
			)
			(2
				(theDoits delete: volumeCode)
				(curRoom newRoom: 560)
			)
		)
	)
)

(instance volumeCode of Obj
	(properties)
	
	(method (doit &tmp temp0 [temp1 50] temp51)
		(if
			(and
				(<= (ego y?) 50)
				(!= local0 (= temp0 (- (ego y?) 3)))
			)
			(theMusic setVol: (= temp51 (/ (* temp0 272) 100)))
			(= local0 temp0)
		)
	)
)

(instance sfx of Sound
	(properties)
)
