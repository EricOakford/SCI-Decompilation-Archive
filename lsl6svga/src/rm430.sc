;;; Sierra Script 1.0 - (do not remove this comment)
(script# 430)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use Polygon)
(use Feature)
(use System)

(public
	rm430 0
)

(local
	local0
	local1
)
(instance rm430 of LarryRoom
	(properties
		noun 16
		picture 430
		horizon 0
		autoLoad 0
	)
	
	(method (init)
		(super init: &rest)
		(leftBreast init:)
		(rightBreast init:)
		(hips init:)
		(legs init:)
		(hands init:)
		(wristBand init:)
		(leftArm init:)
		(rightArm init:)
		(neck init:)
		(herMouth init:)
		(herEyes init:)
		(hair init:)
		(chest init:)
		(face init:)
		(Bset 103)
		(if (Btst 10) (= local1 5))
		(theMusic pause: 1)
		(theMusic2 number: 430 play: setLoop: -1)
		((ScriptID 0 11) init: curRoom)
		(if (Btst 208)
			(self setScript: giveCuffs)
		else
			(theGame handsOn:)
		)
	)
	
	(method (dispose)
		(if (Btst 208)
			(theMusic stop:)
		else
			(theMusic2 fade: 0 10 10 1)
			(theMusic pause: 0 fade: 127 10 5 0)
		)
		((ScriptID 0 11) dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(switch theVerb
				(12
					(curRoom setScript: giveCuffs)
				)
				(2
					(switch local1
						(0 (= temp0 6))
						(1 (= temp0 7))
						(2 (= temp0 8))
						(3 (= temp0 9))
						(4
							(= temp0 10)
							(Bset 10)
							(theGame changeScore: 2 207)
						)
						(else  (= temp0 11))
					)
					(messager say: 16 2 temp0)
					(++ local1)
					(return 1)
				)
				(1
					(if local0
						(messager say: 16 1 1)
						(return 1)
					else
						(++ local0)
						(messager say: 16 1 2)
						(return 1)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (cue)
		(curRoom newRoom: 420)
	)
)

(instance giveCuffs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(theGame handsOff:)
				(messager say: 16 12 0 0 self)
			)
			(2
				(ego put: 18)
				(theGame changeScore: 15 208)
				(= ticks 120)
			)
			(3 (curRoom newRoom: 420))
		)
	)
)

(instance leftBreast of Feature
	(properties
		noun 18
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init: 130 75 141 67 153 78 153 92 135 92
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance rightBreast of Feature
	(properties
		noun 18
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init: 151 74 152 64 166 59 176 71 181 85 165 90 156 86
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance hips of Feature
	(properties
		noun 5
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init: 195 79 206 71 225 69 242 78 254 105 219 121 195 112
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance legs of Feature
	(properties
		noun 6
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init: 221 122 254 105 288 141 241 141
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance hands of Feature
	(properties
		noun 7
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init:
						123
						97
						119
						105
						131
						138
						147
						133
						139
						122
						147
						124
						136
						109
						151
						108
						148
						99
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance wristBand of Feature
	(properties
		noun 8
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init: 148 98 168 96 173 108 150 110
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance leftArm of Feature
	(properties
		noun 9
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init: 132 59 137 96 124 95 118 54
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance rightArm of Feature
	(properties
		noun 9
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init: 165 53 176 47 195 78 199 103 172 105 169 97 184 92
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance neck of Feature
	(properties
		noun 11
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init: 130 55 138 64 163 51 154 42
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance herMouth of Feature
	(properties
		noun 12
		y 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init: 126 43 137 42 142 50 128 53
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance herEyes of Feature
	(properties
		noun 13
		y 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init: 123 32 148 33 148 41 124 41
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance hair of Feature
	(properties
		noun 14
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init:
						116
						53
						127
						49
						122
						39
						126
						28
						137
						25
						148
						30
						156
						46
						174
						44
						161
						15
						147
						7
						123
						8
						112
						23
						109
						40
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance chest of Feature
	(properties
		noun 18
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init: 130 64 165 52 181 83 139 99 132 73
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance face of Feature
	(properties
		noun 3
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init: 128 53 138 55 153 42 146 27 135 25 124 29 122 38
					yourself:
				)
		)
		(super init: &rest)
	)
)
