;;; Sierra Script 1.0 - (do not remove this comment)
(script# 91)
(include game.sh)
(use Main)
(use Intrface)
(use ScumSoft)
(use Motion)
(use Game)
(use System)

(public
	rm91 0
)

(local
	local0
)
(instance rm91 of Room
	(properties
		picture 91
		style WIPEUP
		north 93
		east 92
		west 92
	)
	
	(method (init)
		(super init:)
		(ego
			view: (if (ego has: iVaporizer) 113 else 0)
			setPri: -1
			setCycle: Walk
			setStep: 3 2
			init:
		)
		(trash1 init:)
		(trash2 init:)
		(trash3 init:)
		(trash4 init:)
		(trash5 init:)
		(trash6 init:)
		(trash7 init:)
		(trash8 init:)
		(trash9 init:)
		(trash10 init:)
		(trash11 init:)
		(nerd1 init:)
		(nerd2 init:)
		(nerd3 init:)
		(nerd4 init:)
		(nerd5 init:)
		(nerd6 init:)
		(nerd7 init:)
		(nerd8 init:)
		(nerd9 init:)
		(nerd10 init:)
		(nerd11 init:)
		(fink1 init:)
		(self setRegions: SCUMSOFT)
		(self setScript: rmScript)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look/calendar') (Print 91 0))
					((Said 'look,drink,get/water') (Print 91 1))
					((Said 'look/brush') (Print 91 2))
					((Said 'fart,water/brush') (Print 91 3))
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if
			(and
				scumSoftAlerted
				(== newRoomNumber 90)
				(ego has: iCoveralls)
			)
			(ego posn: (ego x?) 187 setDirection: 0)
		else
			(if (== newRoomNumber 90) (theMusic stop:))
			(timers eachElementDo: #dispose 84)
			(super newRoom: newRoomNumber)
		)
	)
)

(instance rmScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((== prevRoomNum 93) (ego posn: 245 1 setMotion: MoveTo 245 30 self))
					((== prevRoomNum 90)
						(HandsOff)
						(ego posn: 158 190 setMotion: MoveTo 158 183 self)
					)
					(else (self changeState: 1))
				)
			)
			(1
				(rm91 south: 90)
				(HandsOn)
				(if
				(and (== prevRoomNum 90) (not scumSoftAlerted))
					(fink1 setScript: finkScript)
				)
			)
		)
	)
)

(instance fink1 of Fink
	(properties)
	
	(method (init)
		(super init:)
		(self
			posn: 290 157
			setLoop: 7
			setCel: 0
			speakCel: 2
			speakX: 220
			speakY: 160
		)
	)
	
	(method (doit)
		(if (not scumSoftAlerted)
			(cond 
				(
					(not
						(if (<= 0 (finkScript state?))
							(<= (finkScript state?) 2)
						)
					)
					(if (not (ego mover?))
						(cond 
							(cel (fink1 setCel: 0))
							((== 5 (Random 1 10)) (fink1 setCel: 4))
						)
					)
				)
				((and seeProblem (== (ego view?) 92)) (self setScript: finkScript))
			)
		)
		(super doit:)
	)
)

(instance finkScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= global243 1)
				(fink1 setCycle: Forward setMotion: MoveTo 234 187 self)
			)
			(1
				(if (not (ego has: iVaporizer))
					(fink1 setMotion: 0 setCycle: 0 seeProblem: TRUE)
				else
					(self cue:)
				)
			)
			(2
				(fink1
					seeProblem: 0
					setCycle: Forward
					setLoop: 6
					setMotion: MoveTo 290 157 self
				)
			)
			(3
				(= global243 0)
				(fink1 setCycle: 0 setLoop: 7 setCel: 0 stopUpd:)
			)
			(else  (self init:))
		)
	)
)

(instance trash1 of TrashBasket
	(properties
		myID 1
		nearWest 45
		nearNorth 180
		nearEast 98
		nearSouth 190
	)
	
	(method (init)
		(super init:)
		(self posn: 68 182 myNerd: nerd1)
	)
)

(instance trash2 of TrashBasket
	(properties
		myID 2
		nearWest 155
		nearNorth 148
		nearEast 237
		nearSouth 180
	)
	
	(method (init)
		(super init:)
		(self posn: 172 150 myNerd: nerd2)
	)
)

(instance trash3 of TrashBasket
	(properties
		myID 3
		nearWest 28
		nearNorth 112
		nearEast 122
		nearSouth 149
	)
	
	(method (init)
		(super init:)
		(self posn: 56 118 myNerd: nerd3)
	)
)

(instance trash4 of TrashBasket
	(properties
		myID 4
		nearWest 28
		nearNorth 112
		nearEast 122
		nearSouth 149
	)
	
	(method (init)
		(super init:)
		(self posn: 89 118 myNerd: nerd4)
	)
)

(instance trash5 of TrashBasket
	(properties
		myID 5
		nearWest 122
		nearNorth 117
		nearEast 191
		nearSouth 148
	)
	
	(method (init)
		(super init:)
		(self posn: 140 130 myNerd: nerd5)
	)
)

(instance trash6 of TrashBasket
	(properties
		myID 6
		nearWest 208
		nearNorth 39
		nearEast 290
		nearSouth 115
	)
	
	(method (init)
		(super init:)
		(self posn: 275 86 myNerd: nerd6)
	)
)

(instance trash7 of TrashBasket
	(properties
		myID 7
		nearWest 50
		nearNorth 53
		nearEast 124
		nearSouth 85
	)
	
	(method (init)
		(super init:)
		(self posn: 87 55 myNerd: nerd7)
	)
)

(instance trash8 of TrashBasket
	(properties
		myID 8
		nearWest 155
		nearNorth 54
		nearEast 213
		nearSouth 85
	)
	
	(method (init)
		(super init:)
		(self posn: 174 68 myNerd: nerd8)
	)
)

(instance trash9 of TrashBasket
	(properties
		myID 9
		nearWest 208
		nearNorth 39
		nearEast 290
		nearSouth 115
	)
	
	(method (init)
		(super init:)
		(self posn: 244 82 myNerd: nerd9)
	)
)

(instance trash10 of TrashBasket
	(properties
		myID 10
		nearWest 208
		nearNorth 39
		nearEast 290
		nearSouth 115
	)
	
	(method (init)
		(super init:)
		(self posn: 270 64 myNerd: nerd10)
	)
)

(instance trash11 of TrashBasket
	(properties
		myID 11
		nearWest 231
		nearNorth 148
		nearEast 320
		nearSouth 190
	)
	
	(method (init)
		(super init:)
		(self posn: 304 169 myNerd: nerd11)
	)
)

(instance nerd1 of Nerd
	(properties)
	
	(method (init)
		(super init:)
		(self
			setLoop: 1
			posn: 40 184
			speakX: 60
			speakY: 165
			speakCel: 0
		)
	)
)

(instance nerd2 of Nerd
	(properties)
	
	(method (init)
		(super init:)
		(self
			setLoop: 3
			posn: 229 150
			speakX: 227
			speakY: 136
			speakCel: 0
		)
	)
)

(instance nerd3 of Nerd
	(properties)
	
	(method (init)
		(super init:)
		(self
			setLoop: 1
			posn: 26 118
			speakX: 50
			speakY: 100
			speakCel: 0
		)
	)
)

(instance nerd4 of Nerd
	(properties)
	
	(method (init)
		(super init:)
		(self
			setLoop: 0
			posn: 113 121
			speakX: 117
			speakY: 100
			speakCel: 0
		)
	)
)

(instance nerd5 of Nerd
	(properties)
	
	(method (init)
		(super init:)
		(self
			setLoop: 2
			posn: 148 116
			speakX: 170
			speakY: 100
			speakCel: 0
		)
	)
)

(instance nerd6 of Nerd
	(properties)
	
	(method (init)
		(super init:)
		(self
			setLoop: 0
			posn: 299 89
			speakX: 280
			speakY: 70
			speakCel: 1
		)
	)
)

(instance nerd7 of Nerd
	(properties)
	
	(method (init)
		(super init:)
		(self
			setLoop: 1
			posn: 48 56
			speakX: 65
			speakY: 35
			speakCel: 0
		)
	)
)

(instance nerd8 of Nerd
	(properties)
	
	(method (init)
		(super init:)
		(self
			setLoop: 2
			posn: 183 54
			speakX: 200
			speakY: 40
			speakCel: 0
		)
	)
)

(instance nerd9 of Nerd
	(properties)
	
	(method (init)
		(super init:)
		(self
			setLoop: 1
			posn: 216 55
			speakX: 215
			speakY: 35
			speakCel: 1
		)
	)
)

(instance nerd10 of Nerd
	(properties)
	
	(method (init)
		(super init:)
		(self
			setLoop: 0
			posn: 283 57
			speakX: 270
			speakY: 40
			speakCel: 1
		)
	)
)

(instance nerd11 of Nerd
	(properties)
	
	(method (init)
		(super init:)
		(self
			setLoop: 6
			posn: 265 157
			speakX: 280
			speakY: 124
			speakCel: 1
		)
	)
)
