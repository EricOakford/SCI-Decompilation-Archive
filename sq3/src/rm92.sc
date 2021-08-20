;;; Sierra Script 1.0 - (do not remove this comment)
(script# 92)
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
	rm92 0
)

(instance rm92 of Room
	(properties
		picture 92
		style HSHUTTER
		east 91
		west 91
	)
	
	(method (init)
		(super init:)
		(self setRegions: SCUMSOFT)
		(trash1 init:)
		(trash2 init:)
		(trash3 init:)
		(trash4 init:)
		(trash5 init:)
		(trash6 init:)
		(trash7 init:)
		(trash8 init:)
		(nerd1 init:)
		(nerd2 init:)
		(nerd3 init:)
		(nerd4 init:)
		(nerd5 init:)
		(nerd6 init:)
		(nerd7 init:)
		(nerd8 init:)
		(if (InRoom iElmoPicture)
			(Load VIEW 103)
			(photoBoss init:)
			(if (not scumSoftAlerted)
				(Load SOUND 49)
				(pictureSound init:)
				(fink1 init:)
				(copyBeam init:)
			)
		)
		(ego init:)
	)
	
	(method (doit)
		(super doit:)
		(if (ego has: 14)
			(if
				(and
					(not
						(if (<= 0 (finkScript state?))
							(<= (finkScript state?) 3)
						)
					)
					(> (ego x?) 120)
				)
				(fink1 setScript: finkScript)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(if scumSoftAlerted
					(Print 92 0)
					(event claimed: TRUE)
					(return)
				)
				(= temp0 0)
				(cond 
					((Said 'look,explore>')
						(cond 
							((Said '/original')
								(cond 
									(
									(and (InRoom iElmoPicture) (ego inRect: 0 180 200 190)) (= temp0 1) (Print 92 1 #icon 103 0 1 #title {the Boss}))
									((ego has: iElmoPicture) (= temp0 1) ((inventory at: 14) showSelf:))
								)
							)
							((Said '/copier,device')
								(if (ego inRect: 0 147 90 190)
									(= temp0 1)
									(Print 92 2)
								)
							)
							((Said '[/area,partition]')
								(if
								(and (ego inRect: 0 180 200 190) (not (ego has: iElmoPicture)))
									(= temp0 1)
									(Print 92 3)
								)
								(if (ego inRect: 0 147 90 190)
									(= temp0 1)
									(Print 92 4)
								)
							)
						)
					)
					((Said 'get,remove/original')
						(= temp0 1)
						(cond 
							((ego has: iElmoPicture) (Print 92 5))
							((ego inRect: 83 180 111 183) (ego setScript: getPic))
							(else (Print 92 6))
						)
					)
					((Said 'hang,drop,replace,replace/original')
						(if
						(and (ego inRect: 83 180 111 183) (ego has: iElmoPicture))
							(= temp0 1)
							(ego setScript: putPic)
						)
					)
					((Said 'hang,drop,replace,replace/copy')
						(if
						(and (ego inRect: 83 180 111 183) (ego has: iElmoPictureCopy))
							(= temp0 1)
							(Print 92 7)
						)
					)
					((Said 'converse/man')
						(if (< (ego distanceTo: fink1) 40)
							(= temp0 1)
							(Print 92 8)
							(Print 92 9)
						)
					)
					(
						(or
							(Said 'use,jog,begin,turn/copier,device')
							(Said 'copy[/original,elmo]')
							(Said 'make/copy[<original,elmo]')
						)
						(if (ego inRect: 22 154 43 157)
							(= temp0 1)
							(if (ego has: iElmoPicture)
								(if (ego has: iElmoPictureCopy)
									(Print 92 10)
								else
									(ego setScript: copyPic)
								)
							else
								(Print 92 11)
							)
						)
					)
					((Said 'copy') (= temp0 1) (Print 92 12))
				)
				(if (and (event claimed?) (not temp0))
					(event claimed: FALSE)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(timers eachElementDo: #dispose 84)
		(super newRoom: newRoomNumber)
	)
)

(instance getPic of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego has: iElmoPictureCopy) (Print 92 13) else (Print 92 14))
				(HandsOff)
				(photoBoss hide:)
				(pictureSound play:)
				(ego
					x: (photoBoss x?)
					view: 103
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(ego
					view: 113
					setLoop: -1
					setDirection: 1
					setCycle: Walk
					get: 14
				)
				(HandsOn)
			)
			(else  (self changeState: 0))
		)
	)
)

(instance putPic of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego has: iElmoPictureCopy) (Print 92 15) else (Print 92 16))
				(HandsOff)
				(pictureSound play:)
				(ego
					x: (photoBoss x?)
					view: 103
					setLoop: 1
					setCel: (ego lastCel:)
					setCycle: BegLoop self
				)
			)
			(1
				(photoBoss show:)
				(ego
					view: 113
					setLoop: -1
					setDirection: 1
					setCycle: Walk
					put: 14 curRoomNum
				)
				(HandsOn)
			)
			(else  (self changeState: 0))
		)
	)
)

(instance copyPic of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 92 17)
				(ego
					view: 103
					posn: 32 155
					setLoop: 1
					setCel: (ego lastCel:)
					setCycle: BegLoop self
				)
			)
			(1
				(ego setPri: 15)
				(copyBeam
					show:
					setPri: 14
					posn: 41 135
					setMotion: MoveTo 29 135 self
				)
			)
			(2 (Timer setReal: self 2))
			(3
				(copyBeam posn: 41 135 setMotion: MoveTo 29 135 self)
			)
			(4
				(copyBeam dispose:)
				(ego setPri: -1)
				(Print 92 18)
				(ego setCycle: EndLoop self)
			)
			(5
				(Print 92 19)
				(ego setCycle: EndLoop self)
			)
			(6
				(ego
					view: 113
					setDirection: 1
					setLoop: 3
					setCel: -1
					setCycle: Walk
					get: 15
					forceUpd:
				)
				(ego setLoop: -1)
				(theGame changeScore: 5)
				(HandsOn)
			)
			(else  (self changeState: 0))
		)
	)
)

(instance finkScript of Script
	(properties)
	
	(method (changeState newState)
		(if scumSoftAlerted (return))
		(switch (= state newState)
			(0
				(= global243 1)
				(fink1
					setLoop: 7
					setCycle: Forward
					setMotion: MoveTo 190 162 self
				)
			)
			(1
				(fink1 setMotion: MoveTo 128 185 self)
			)
			(2
				(if (ego has: iElmoPicture)
					(fink1 setMotion: 0 setCycle: 0 seeProblem: TRUE)
				else
					(= cycles 1)
				)
			)
			(3
				(fink1 setLoop: 6 setMotion: MoveTo 190 162 self)
			)
			(4
				(if (ego has: 14)
					(self init:)
				else
					(fink1 setMotion: MoveTo 340 155 self)
				)
			)
			(5
				(= global243 0)
				(fink1 stopUpd:)
			)
			(else  (self init:))
		)
	)
)

(instance fink1 of Fink
	(properties)
	
	(method (init)
		(super init:)
		(self
			speakX: 118
			speakY: 156
			speakCel: 1
			posn: 340 155
			ignoreActors: 0
			ignoreControl: -1
			stopUpd:
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(< (self distanceTo: ego) 20)
				(ego has: iElmoPicture)
				(== (ego view?) 113)
			)
			(self
				setMotion: 0
				setCycle: 0
				speakX: (- x 10)
				speakY: (- y 30)
				seeProblem: 1
				stopUpd:
			)
		)
	)
)

(instance photoBoss of View
	(properties)
	
	(method (init)
		(super init:)
		(self
			setPri: 14
			view: 103
			setLoop: 0
			setCel: 0
			posn: 97 171
			stopUpd:
		)
	)
)

(instance copyBeam of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self view: 115 setLoop: 9 setCel: 2 moveSpeed: 2 hide:)
	)
)

(instance trash1 of TrashBasket
	(properties
		myID 13
		nearWest 85
		nearNorth 148
		nearEast 190
		nearSouth 179
	)
	
	(method (init)
		(super init:)
		(self posn: 120 150 myNerd: nerd1)
	)
)

(instance trash2 of TrashBasket
	(properties
		myID 14
		nearWest 21
		nearNorth 115
		nearEast 122
		nearSouth 147
	)
	
	(method (init)
		(super init:)
		(self posn: 48 118 myNerd: nerd2)
	)
)

(instance trash3 of TrashBasket
	(properties
		myID 15
		nearWest 122
		nearNorth 117
		nearEast 191
		nearSouth 147
	)
	
	(method (init)
		(super init:)
		(self posn: 153 118 myNerd: nerd3)
	)
)

(instance trash4 of TrashBasket
	(properties
		myID 16
		nearWest 193
		nearNorth 116
		nearEast 253
		nearSouth 148
	)
	
	(method (init)
		(super init:)
		(self posn: 221 128 myNerd: nerd4)
	)
)

(instance trash5 of TrashBasket
	(properties
		myID 17
		nearWest 123
		nearNorth 85
		nearEast 187
		nearSouth 115
	)
	
	(method (init)
		(super init:)
		(self posn: 158 87 myNerd: nerd5)
	)
)

(instance trash6 of TrashBasket
	(properties
		myID 18
		nearWest 21
		nearNorth 53
		nearEast 66
		nearSouth 85
	)
	
	(method (init)
		(super init:)
		(self posn: 43 56 myNerd: nerd6)
	)
)

(instance trash7 of TrashBasket
	(properties
		myID 19
		nearWest 76
		nearNorth 23
		nearEast 156
		nearSouth 83
	)
	
	(method (init)
		(super init:)
		(self posn: 106 54 myNerd: nerd7)
	)
)

(instance trash8 of TrashBasket
	(properties
		myID 20
		nearWest 183
		nearNorth 54
		nearEast 246
		nearSouth 115
	)
	
	(method (init)
		(super init:)
		(self posn: 210 68 myNerd: nerd8)
	)
)

(instance nerd1 of Nerd
	(properties)
	
	(method (init)
		(super init:)
		(self
			setLoop: 0
			posn: 101 153
			speakX: 110
			speakY: 137
			speakCel: 0
		)
	)
)

(instance nerd2 of Nerd
	(properties)
	
	(method (init)
		(super init:)
		(self
			setLoop: 2
			posn: 66 116
			speakX: 92
			speakY: 105
			speakCel: 0
		)
	)
)

(instance nerd3 of Nerd
	(properties)
	
	(method (init)
		(super init:)
		(self
			setLoop: 3
			posn: 182 118
			setPri: 11
			speakX: 160
			speakY: 102
			speakCel: 1
		)
	)
)

(instance nerd4 of Nerd
	(properties)
	
	(method (init)
		(super init:)
		(self
			setLoop: 2
			posn: 201 112
			setPri: 8
			speakX: 228
			speakY: 93
			speakCel: 0
		)
	)
)

(instance nerd5 of Nerd
	(properties)
	
	(method (init)
		(super init:)
		(self
			setLoop: 1
			posn: 170 89
			speakX: 159
			speakY: 68
			speakCel: 1
		)
	)
)

(instance nerd6 of Nerd
	(properties)
	
	(method (init)
		(super init:)
		(self
			setPri: 3
			setLoop: 1
			posn: 55 59
			speakX: 69
			speakY: 38
			speakCel: 0
		)
	)
)

(instance nerd7 of Nerd
	(properties)
	
	(method (init)
		(super init:)
		(self
			setLoop: 0
			posn: 91 58
			speakX: 99
			speakY: 38
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
			posn: 196 55
			speakX: 184
			speakY: 42
			speakCel: 1
		)
	)
)

(instance pictureSound of Sound
	(properties
		number 49
		priority 1
	)
)
