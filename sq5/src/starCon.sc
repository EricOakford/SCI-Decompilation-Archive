;;; Sierra Script 1.0 - (do not remove this comment)
(script# rgStarCon)
(include game.sh) (include "109.shm")
(use Main)
(use Talker)
(use Feature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	starCon 0
	sTestTimer 1
	sCrestTimer 2
	sExitSouth 3
	sExitNorth 4
	sEnterSouth 5
	sEnterNorth 6
	cadetsTalker 7
)

(local
	theSeq
	local1 =  -1
)
(class starCon of Region
	
	(method (init)
		(super init: &rest)
		(narrator y: 20)
		(if (OneOf curRoomNum 121 125 122 123 117 115)
			(ego baseSetter: myBaseSetter)
		else
			(ego baseSetter: 0)
		)
		(cond 
			((OneOf curRoomNum 115 117 119 121 122 123 125)
				(if (not (OneOf prevRoomNum 115 117 119 121 122 123 125 135))
					(theMusic1 number: 5 setLoop: -1 play:)
				)
			)
			((OneOf prevRoomNum 115 117 119 121 122 123 125)
				(theMusic1 fade: 0 10 5 1)
			)
		)
		(if (OneOf curRoomNum 121 122 123 125)
			(hallPeople init:)
			(bayFeature init: setOnMeCheck: ftrControl cBROWN)
		)
	)
	
	(method (dispose)
		(narrator y: -1)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (OneOf curRoomNum 121 122 123 125)
					(messager
						say: N_HALL V_LOOK NULL (+ (= local1 (mod (++ local1) 4)) 1) 0 109
					)
				)
			)
			(V_SCRUBBER
				(messager say: NULL V_SCRUBBER C_CANT_DO 0 0 109)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (newRoom n)
		(= keep
			(OneOf n
				110 115 117 119 121 122 123 125 127 132 133 135
				137 165 166 195
			)
		)
		(= initialized FALSE)
		(if (OneOf curRoomNum 121 125 122 123)
			(hallPeople dispose:)
		)
		(super newRoom: n)
	)
)

(instance hallPeople of Prop
	(properties
		noun N_HALL_PEOPLE
		view 147
		signal ignrAct
	)
	
	(method (init)
		(switch curRoomNum
			(121
				(self
					loop: 0
					cel: 0
					x: 110
					y: 146
					approachX: 130
					approachY: 151
				)
				(= theSeq 1)
			)
			(122
				(self
					loop: 1
					cel: 0
					x: 127
					y: 130
					approachX: 131
					approachY: 138
				)
				(= theSeq 2)
			)
			(123
				(self
					loop: 3
					cel: 0
					x: 219
					y: 135
					approachX: 218
					approachY: 142
				)
				(= theSeq 3)
				(super init:)
				(self approachVerbs: V_DO V_COMMAND V_TALK setScript: sHallPeople)
			)
			(125
				(self
					loop: 2
					cel: 0
					x: 105
					y: 148
					approachX: 126
					approachY: 142
				)
				(= theSeq 4)
			)
		)
		(if (and (Btst fAttendedClass) (OneOf curRoomNum 121 122 125))
			(super init:)
			(self approachVerbs: 4 24 2 setScript: sHallPeople)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(messager say: noun theVerb NULL theSeq 0 109)
			)
			(V_LOOK
				(messager say: noun theVerb NULL theSeq 0 109)
			)
			(V_TALK
				(messager say: noun theVerb NULL theSeq 0 109)
			)
			(V_COMMAND
				(messager say: noun theVerb NULL theSeq 0 109)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bayFeature of Feature
	(properties
		x 5
		y 5
		noun N_BAY
		modNum 109
		onMeCheck cBROWN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager
					say: N_BAY V_LOOK NULL (+ (= local1 (mod (++ local1) 4)) 1) 0 109
				)
			)
			(V_SCRUBBER
				(messager say: NULL V_SCRUBBER C_CANT_DO 0 0 109)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance myBaseSetter of Code
	
	(method (doit obj &tmp bottom right left top)
		(= bottom
			(/ (- (obj nsBottom?) (obj nsTop?)) 20)
		)
		(= right
			(/
				(*
					(CelWide (obj view?) (obj loop?) (obj cel?))
					(obj scaleX?)
				)
				140
			)
		)
		(= left (- (obj x?) (/ right 2)))
		(= top (- (obj y?) (/ bottom 2)))
		(obj
			brLeft: left
			brRight: (+ left right)
			brTop: top
			brBottom: (+ top bottom)
		)
	)
)

(instance sHallPeople of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(hallPeople setCycle: EndLoop self)
			)
			(1 (= seconds (Random 1 2)))
			(2
				(hallPeople setCycle: BegLoop self)
			)
			(3
				(= state -1)
				(= seconds (Random 4 7))
			)
			(4 (self dispose:))
		)
	)
)

(instance sTestTimer of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 300))
			(1
				(messager say: N_TIMER NULL C_TEST 1 self 109)
			)
			(2 (= seconds 180))
			(3
				(messager say: N_TIMER NULL C_TEST 2 self 109)
			)
			(4 (= seconds 60))
			(5
				(messager say: N_TIMER NULL C_TEST 3 self 109)
			)
			(6 (= seconds 30))
			(7
				(curRoom newRoom: 195)
				(self dispose:)
			)
		)
	)
)

(instance sCrestTimer of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 300))
			(1
				(messager say: N_TIMER NULL C_CREST 1 self 109)
			)
			(2 (= seconds 180))
			(3
				(messager say: N_TIMER NULL C_CREST 2 self 109)
			)
			(4 (= seconds 60))
			(5
				(messager say: N_TIMER NULL C_CREST 3 self 109)
			)
			(6 (= seconds 30))
			(7
				(curRoom newRoom: 195)
				(self dispose:)
			)
		)
	)
)

(instance sExitSouth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(curRoom newRoom: register)
			)
		)
	)
)

(instance sEnterSouth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setPri: -1
					setLoop: 3
					posn: 160 160
					setMotion: MoveTo 160 140 self
				)
			)
			(1
				(ego setLoop: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterNorth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setPri: 0
					x: 115
					y: 105
					setMotion: MoveTo 143 107 self
				)
			)
			(1
				(ego setMotion: MoveTo 147 112 self)
			)
			(2
				(ego setPri: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitNorth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setPri: 0 setMotion: MoveTo 115 105 self)
			)
			(1 (curRoom newRoom: register))
		)
	)
)

(instance cadetsTalker of Narrator
	
	(method (init)
		(= font userFont)
		(= systemWindow theSpeakWindow)
		(switch curRoomNum
			(121
				(systemWindow tailX: 100 tailY: 90 xOffset: 0)
			)
			(122
				(systemWindow tailX: 110 tailY: 90 xOffset: 0)
			)
			(123
				(systemWindow tailX: 225 tailY: 90 xOffset: 1)
			)
			(125
				(systemWindow tailX: 104 tailY: 85 xOffset: 6)
			)
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)
