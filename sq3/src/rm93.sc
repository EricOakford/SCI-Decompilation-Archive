;;; Sierra Script 1.0 - (do not remove this comment)
(script# 93)
(include game.sh)
(use Main)
(use Intrface)
(use ScumSoft)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm93 0
)

(instance rm93 of Room
	(properties
		picture 93
		style HSHUTTER
		east 94
		south 91
	)
	
	(method (init)
		(super init:)
		(self setRegions: SCUMSOFT)
		(Load VIEW 131)
		(Load VIEW 132)
		(Load VIEW 133)
		(Load SOUND 54)
		(addToPics
			add:
				prog01
				prog02
				prog03
				prog04
				prog05
				prog06
				prog07
				prog08
				prog09
				prog10
				prog11
				prog12
				prog13
				prog14
				prog15
				prog16
				prog17
				prog18
				prog19
				prog20
				prog21
				prog22
				prog23
				prog24
				prog25
				prog26
		)
		(addToPics doit:)
		(rick init:)
		(ken init:)
		(trash1 init:)
		(ego init:)
		(if elmoAtDesk
			(= elmoAtDesk FALSE)
			(trash1 myNerd: 0)
			(if ((inventory at: iKeycard) ownedBy: curRoomNum)
				(keycard init:)
			)
		else
			(= elmoAtDesk TRUE)
			(elmo init:)
			(trash1 setCel: (= [trashVaporized 0] 0))
			(trash1 vaporized: 0)
		)
		(self setScript: rmScript)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look[/area]')
						(if elmoAtDesk
							(Print 93 0)
						else
							(Print 93 1)
						)
					)
					((Said 'look/man,boss')
						(if (and elmoAtDesk (ego inRect: 225 86 320 149))
							(Print 93 2)
						else
							(Print 93 3)
						)
					)
					((Said '*/complex')
						(Print 93 4)
					)
					((Said 'converse/man,boy,elmo,boss,bystander')
						(if (and elmoAtDesk (ego inRect: 225 86 320 149))
							(Print 93 5)
						else
							(Print 93 6)
						)
					)
					((Said 'look,open,explore,unlock/desk,drawer')
						(if (ego inRect: 225 86 320 149)
							(cond 
								(elmoAtDesk
									(Print 93 7)
								)
								(((inventory at: iKeycard) ownedBy: curRoomNum)
									(Print 93 8)
								)
								(else
									(Print 93 9)
								)
							)
						else
							(Print 93 10)
						)
					)
					((Said 'get/key,card')
						(cond 
							(elmoAtDesk
								(Print 93 11)
							)
							(((inventory at: iKeycard) ownedBy: curRoomNum)
								(if (ego inRect: 275 89 300 104)
									(ego get: iKeycard)
									(keycard dispose:)
									(Print 93 12)
									(theGame changeScore: 5)
								else
									(Print 93 13)
								)
							)
							((ego has: iKeycard)
								(Print 93 14)
							)
							(else
								(Print 93 15)
							)
						)
					)
				)
			)
		)
	)
	
	(method (newRoom n)
		(if (== n 94)
			(theMusic stop:)
		)
		(timers eachElementDo: #dispose 84)
		(super newRoom: n)
	)
)

(instance rmScript of Script
	(method (init)
		(super init: &rest)
		(if (== prevRoomNum 94)
			(ego posn: 318 65 view: 113)
		)
		(ego init:)
		(if (== prevRoomNum 94)
			(ego setMotion: MoveTo 288 65 self)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (not scumSoftAlerted)
			(if
				(and
					(== (ken loop?) 0)
					(< (rick loop?) 2)
					(== 10 (Random 1 20))
				)
				(if (== (kenScript state?) 1)
					(kenScript start: 1)
				else
					(kenScript start: 0)
				)
				(ken setScript: kenWhip)
			)
			(if
				(and
					(== (rick loop?) 0)
					(< (ken loop?) 2)
					(== 10 (Random 1 20))
				)
				(if (== (rickScript state?) 0)
					(rickScript start: 0)
				else
					(rickScript start: 1)
				)
				(rick setScript: rickWhip)
			)
		)
	)
)

(instance trash1 of TrashBasket
	(properties
		nearWest 225
		nearNorth 86
		nearEast 320
		nearSouth 149
	)
	
	(method (init)
		(super init:)
		(self posn: 256 103 myNerd: elmo)
	)
)

(instance elmo of Nerd
	(method (init)
		(super init:)
		(self
			view: 115
			setLoop: 8
			posn: 263 84
			setPri: 6
			speakX: 167
			speakY: 75
			speakCel: 0
		)
	)
)

(instance keycard of View
	(method (init)
		(self
			view: 115
			setLoop: 9
			setCel: 1
			setPri: 6
			posn: 285 92
		)
		(super init:)
	)
)

(class ProgPri14 of PicView	;EO: was a class, but not in the class table
	(properties
		heading 0
		yStep 133
		cel 14
		priority 16384
	)
)

(class ProgPri4 of PicView	;EO: was a class, but not in the class table
	(properties
		heading 0
		view 133
		loop 0
		cel 1
		priority 4
		signal ignrAct
	)
)

(class ProgPri2 of PicView	;EO: was a class, but not in the class table
	(properties
		heading 0
		view 133
		loop 0
		cel 1
		priority 2
		signal ignrAct
	)
)

(instance prog01 of ProgPri14
	(properties
		y 181
		x 176
		yStep 133
		view 0
		cel 14
		priority 16384
	)
)

(instance prog02 of ProgPri14
	(properties
		y 150
		x 175
		yStep 133
		view 0
		cel 14
		priority 16384
	)
)

(instance prog03 of ProgPri14
	(properties
		y 118
		x 173
		yStep 133
		view 0
		cel 14
		priority 16384
	)
)

(instance prog04 of ProgPri14
	(properties
		y 87
		x 171
		yStep 133
		view 0
		cel 14
		priority 16384
	)
)

(instance prog05 of ProgPri14
	(properties
		y 54
		x 170
		yStep 133
		view 0
		cel 14
		priority 16384
	)
)

(instance prog06 of ProgPri14
	(properties
		y 24
		x 170
		yStep 133
		view 0
		cel 14
		priority 16384
	)
)

(instance prog07 of ProgPri14
	(properties
		y 181
		x 134
		yStep 133
		view 0
		cel 14
		priority 16384
	)
)

(instance prog08 of ProgPri14
	(properties
		y 150
		x 138
		yStep 133
		view 0
		cel 14
		priority 16384
	)
)

(instance prog09 of ProgPri14
	(properties
		y 118
		x 140
		yStep 133
		view 0
		cel 14
		priority 16384
	)
)

(instance prog10 of ProgPri14
	(properties
		y 87
		x 141
		yStep 133
		view 0
		cel 14
		priority 16384
	)
)

(instance prog11 of ProgPri14
	(properties
		y 55
		x 141
		yStep 133
		view 0
		cel 14
		priority 16384
	)
)

(instance prog12 of ProgPri14
	(properties
		y 24
		x 143
		yStep 133
		view 0
		cel 14
		priority 16384
	)
)

(instance prog13 of ProgPri4
	(properties
		y 181
		x 56
	)
)

(instance prog14 of ProgPri4
	(properties
		y 150
		x 63
	)
)

(instance prog15 of ProgPri4
	(properties
		y 118
		x 70
	)
)

(instance prog16 of ProgPri4
	(properties
		y 87
		x 78
	)
)

(instance prog17 of ProgPri4
	(properties
		y 55
		x 88
	)
)

(instance prog18 of ProgPri4
	(properties
		y 24
		x 96
	)
)

(instance prog19 of ProgPri2
	(properties
		y 181
		x 16
	)
)

(instance prog20 of ProgPri2
	(properties
		y 150
		x 26
	)
)

(instance prog21 of ProgPri2
	(properties
		y 118
		x 37
	)
)

(instance prog22 of ProgPri2
	(properties
		y 87
		x 48
	)
)

(instance prog23 of ProgPri2
	(properties
		y 55
		x 59
	)
)

(instance prog24 of ProgPri2
	(properties
		y 24
		x 72
	)
)

(instance prog25 of ProgPri2
	(properties
		y 24
		x 26
	)
)

(instance prog26 of ProgPri2
	(properties
		y 56
		x 10
	)
)

(instance rick of Actor
	(method (init)
		(self
			ignoreHorizon: TRUE
			view: 131
			x: 116
			y: -5
			setPri: 14
			setCycle: Forward
			setStep: 1 1
			ignoreActors: TRUE
			ignoreControl: -1
			setScript: rickScript
		)
		(super init:)
	)
)

(instance rickScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rick setLoop: 0 setMotion: MoveTo 97 148 self)
			)
			(1
				(rick setLoop: 1 setMotion: MoveTo 116 -5 self)
			)
			(2
				(self init:)
			)
		)
	)
)

(instance ken of Actor
	(method (init)
		(self
			ignoreHorizon: TRUE
			view: 132
			x: -11
			y: 136
			setPri: 14
			setCycle: Forward
			setStep: 1 1
			ignoreActors: TRUE
			ignoreControl: -1
			setScript: kenScript
		)
		(super init:)
	)
)

(instance kenScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ken setLoop: 1 setMotion: MoveTo 48 -5 self)
			)
			(1
				(ken setLoop: 0 setMotion: MoveTo -11 136 self)
			)
			(2
				(kenScript start: 0)
				(self init:)
			)
		)
	)
)

(instance rickWhip of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rick
					setLoop: (if (<= 5 (Random 1 10)) 2 else 3)
					setCel: 0
					setMotion: 0
					setCycle: EndLoop self
				)
				(whipSound play:)
			)
			(1
				(if (!= (whipSound prevSignal?) -1)
					(-- state)
					(Timer setCycle: self 3)
				else
					(rick setCycle: Forward setLoop: 0 setScript: rickScript)
				)
			)
			(else  (self init:))
		)
	)
)

(instance kenWhip of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ken setLoop: 2 setCel: 0 setMotion: 0 setCycle: EndLoop self)
				(whipSound play:)
			)
			(1
				(if (!= (whipSound prevSignal?) -1)
					(-- state)
					(Timer setCycle: self 3)
				else
					(ken setCycle: Forward setLoop: 0 setScript: kenScript)
				)
			)
			(else
				(self init:)
			)
		)
	)
)

(instance whipSound of Sound
	(properties
		number 54
		priority 1
	)
)
