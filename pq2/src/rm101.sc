;;; Sierra Script 1.0 - (do not remove this comment)
(script# 101)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm101 0
)

(local
	willy
	radio1
	radio2
	officeDoor
	local4
	goingToThePark
	local6
	local7
	local8
	local9
	local10
	gotRadios
	local12
	local13
	local14
	local15
)
(procedure (localproc_000c)
	(Print &rest #at -1 24)
)

(instance rm101 of Room
	(properties
		picture 101
		style $0000
	)
	
	(method (init)
		(HandsOn)
		(User canInput: 1 canControl: 1)
		(= gunFireState 3)
		(= gunNotNeeded 1)
		(Load rsVIEW 272)
		(Load rsVIEW 20)
		(Load rsVIEW 45)
		(super init:)
		((View new:)
			view: 272
			posn: 227 139
			loop: 0
			cel: 1
			setPri: 10
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 272
			posn: 205 142
			loop: 0
			cel: 0
			setPri: 10
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 272
			posn: 74 129
			loop: 1
			cel: 1
			setPri: 9
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 272
			posn: 103 129
			loop: 1
			cel: 0
			setPri: 9
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 272
			posn: 50 145
			loop: 1
			cel: 0
			setPri: 11
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 272
			posn: 73 94
			setPri: 0
			loop: 2
			cel: 2
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 272
			posn: 272 113
			setPri: 0
			loop: 2
			cel: 3
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 272
			posn: 51 131
			setPri: 1
			loop: 3
			cel: 1
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 272
			posn: 72 146
			setPri: 15
			loop: 3
			cel: 0
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 272
			posn: 48 92
			setPri: 0
			loop: 2
			cel: 4
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 272
			posn: 33 92
			setPri: 0
			loop: 2
			cel: 5
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 272
			posn: 34 108
			setPri: 0
			loop: 2
			cel: 6
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 272
			posn: 133 146
			setPri: 15
			loop: 2
			cel: 0
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 272
			posn: 219 94
			setPri: 0
			loop: 2
			cel: 7
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 272
			posn: 117 96
			setPri: 0
			loop: 2
			cel: 7
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 272
			posn: 131 89
			setPri: 0
			loop: 2
			cel: 4
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 272
			posn: 218 109
			setPri: 0
			loop: 2
			cel: 5
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 272
			posn: 20 179
			setPri: 0
			loop: 2
			cel: 5
			init:
			ignoreActors:
			addToPic:
		)
		((= radio1 (Prop new:))
			view: 272
			posn: 137 124
			setPri: 15
			loop: 2
			cel: 1
			init:
			ignoreActors:
		)
		((= radio2 (Prop new:))
			view: 272
			posn: 131 123
			setPri: 15
			loop: 2
			cel: 1
			init:
			ignoreActors:
		)
		((= officeDoor (Prop new:))
			view: 272
			posn: 148 137
			setPri: 6
			loop: 4
			cel: 0
			ignoreActors:
			init:
		)
		((= keith (Actor new:))
			view: 20
			posn: 10 135
			loop: 0
			setCycle: Walk
			setPri: 9
			setMotion: Follow ego 15
			init:
			illegalBits: -16384
		)
		((= willy (Actor new:))
			view: 45
			posn: 245 141
			setCycle: Walk
			init:
			stopUpd:
		)
		(NormalEgo)
		(ego
			view: 1
			posn: 20 137
			loop: 0
			setPri: 9
			setLoop: -1
			init:
			illegalBits: -16384
		)
		(self setScript: rm101Script)
	)
	
	(method (doit)
		(cond 
			((> local15 1) (-- local15))
			((== local15 1) (= local15 0) (tryAgain cue:))
		)
		(if
		(and (ego inRect: 135 135 153 139) (not local13))
			(theDoor changeState: 1)
		)
		(if
		(and (not local13) (ego inRect: 110 129 130 139))
			(if (not local4)
				(ego illegalBits: 0)
				(keith illegalBits: 0)
				(LtSpeech changeState: 0)
			else
				(tryAgain changeState: 1)
			)
		)
		(cond 
			(
			(and (< (ego x?) 20) local4 (not goingToThePark))
				(= goingToThePark 1)
				(ego illegalBits: -32768)
				(keith illegalBits: -32768)
				(self setScript: toTheParkScript)
			)
			(
			(and (< (ego x?) 20) (not local4) (not local14)) (= local14 1) (localproc_000c 101 0))
		)
		(if (and (> (ego x?) 20) local14) (= local14 0))
		(super doit:)
	)
	
	(method (dispose)
		(toTheParkScript dispose:)
		(super dispose:)
	)
)

(instance rm101Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 40 137 self)
			)
			(1 (localproc_000c 101 42 83))
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said 'look>')
						(cond 
							((Said '/wall') (localproc_000c 101 1))
							((Said '/pushpin,pin[<press]') (localproc_000c 101 2))
							((Said '/map') (localproc_000c 101 3))
							((or (Said '/ceiling') (Said '<up')) (localproc_000c 101 4))
							((or (Said '/floor,dirt') (Said '<down')) (localproc_000c 101 5))
							((Said '/dude')
								(if (< (ego x?) 137)
									(localproc_000c 101 6)
								else
									(localproc_000c 101 7)
								)
							)
							((Said '/lieutenant,willie,cole,miller')
								(if (< (ego x?) 137)
									(localproc_000c 101 8)
								else
									(localproc_000c 101 7)
								)
							)
							((Said '/broad,broad')
								(if (< (ego x?) 137)
									(localproc_000c 101 9)
								else
									(localproc_000c 101 10)
								)
							)
							((Said '/extender')
								(if (> (ego x?) 137)
									(localproc_000c 101 11)
								else
									(localproc_000c 101 12)
								)
							)
							((Said '/desk')
								(cond 
									((> (ego x?) 137) (localproc_000c 101 13))
									(
									(or (ego inRect: 60 133 80 139) (== (ego loop?) 2)) (localproc_000c 101 14))
									(
									(or (ego inRect: 30 133 60 139) (== (ego loop?) 3)) (localproc_000c 101 15))
								)
							)
							((Said '/table')
								(if (> (ego x?) 137)
									(localproc_000c 101 16)
								else
									(localproc_000c 101 17)
								)
							)
							((Said '[<at,around][/!*,chamber,office]') (localproc_000c 101 18))
							((Said '/file,cabinet')
								(if (> (ego x?) 137)
									(localproc_000c 101 19)
								else
									(localproc_000c 101 20)
								)
							)
						)
					)
					((Said 'chat>')
						(cond 
							((Said '/broad,broad')
								(cond 
									(
									(and (not local4) (== local6 0) (< (ego x?) 137)) (localproc_000c 101 21) (= local6 1))
									(
									(and (not local4) (== local6 1) (< (ego x?) 137)) (localproc_000c 101 22) (= local6 2))
									((and (== local6 2) (< (ego x?) 137)) (localproc_000c 101 23))
									((and local4 (< (ego x?) 137)) (localproc_000c 101 24))
									(else (localproc_000c 101 25))
								)
							)
							((Said '/lieutenant,willie,cole,miller')
								(cond 
									((< (ego x?) 137)
										(if (not local4)
											(localproc_000c 101 22)
										else
											(localproc_000c 101 26)
										)
									)
									((not local9)
										(cond 
											((not local8) (localproc_000c 101 27) (= local8 1))
											(local8 (localproc_000c 101 28) (= local9 1))
										)
									)
									(else (localproc_000c 101 29))
								)
							)
							((Said '/dude')
								(cond 
									((< (ego x?) 137)
										(if (not local4)
											(cond 
												((== local6 0) (localproc_000c 101 24) (= local6 1))
												((== local6 1) (localproc_000c 101 22) (= local6 2))
												((== local6 2) (localproc_000c 101 23))
											)
										else
											(localproc_000c 101 26)
										)
									)
									((not local9)
										(cond 
											((not local8) (localproc_000c 101 27) (= local8 1))
											(local8 (localproc_000c 101 30) (= local9 1))
										)
									)
									(else (localproc_000c 101 29))
								)
							)
						)
					)
					((Said '/n') (localproc_000c 101 31))
					((Said 'affirmative') (localproc_000c 101 32))
					((Said 'open/file,cabinet')
						(if (> (ego x?) 137)
							(localproc_000c 101 33)
						else
							(localproc_000c 101 34)
						)
					)
					((or (Said 'knock') (Said 'open/door')) (if (not local4) (Print 101 35) else (Print 101 36)))
					(
						(or
							(Said 'use,dial/phone,phone')
							(Said 'place,make/call')
						)
						(localproc_000c 101 37)
					)
					((Said 'get,get/extender,walkie,talkie')
						(cond 
							(
								(and
									(ego inRect: 130 135 160 150)
									(== (ego loop?) 2)
									(not (ego has: 30))
								)
								(if (not gotRadios)
									(SolvePuzzle 3)
									(localproc_000c 101 38 83)
									(willy loop: 1)
									(localproc_000c 101 39 83)
									(willy loop: 0)
									(radio1 dispose:)
									(radio2 dispose:)
									(ego get: 30)
								else
									(SolvePuzzle 3)
									(localproc_000c 101 40 83)
									(radio1 dispose:)
									(radio2 dispose:)
									(ego get: 30)
								)
							)
							((ego has: 30) (localproc_000c 101 41))
							(else (localproc_000c 101 35))
						)
					)
				)
			else
				0
			)
		)
	)
)

(instance LtSpeech of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local13 1)
				(willy loop: 1)
				(HandsOff)
				(self cue:)
			)
			(1
				(localproc_000c 101 43 83)
				(officeDoor setCycle: EndLoop self)
			)
			(2
				(ego setMotion: MoveTo 180 139 self)
				(keith setMotion: MoveTo 165 136 ignoreActors:)
			)
			(3
				(ego setLoop: 2 setCel: 1)
				(keith setLoop: 2 setCel: 1 ignoreActors:)
				(self cue:)
			)
			(4
				(willy setMotion: MoveTo 245 146 self)
			)
			(5
				(willy setMotion: MoveTo 170 146 self)
			)
			(6
				(willy setLoop: 3 setCel: 0)
				(localproc_000c 101 44 83)
				(localproc_000c 101 45 83)
				(localproc_000c 101 46 83)
				(localproc_000c 101 47 83)
				(localproc_000c 101 48 83)
				(localproc_000c 101 49 83)
				(localproc_000c 101 50 83)
				(localproc_000c 101 51 83)
				(localproc_000c 101 52 83)
				(localproc_000c 101 53 83)
				(localproc_000c 101 54 83)
				(localproc_000c 101 55 83)
				(= local4 1)
				(self cue:)
			)
			(7
				(= local13 0)
				(willy setLoop: -1 setCel: -1)
				(ego setPri: -1 setLoop: -1 setCel: -1 illegalBits: -8192)
				(keith
					setPri: -1
					setLoop: 2
					setCel: -1
					illegalBits: -16384
				)
				(HandsOn)
				(willy setMotion: MoveTo 265 146 self)
			)
			(8 (willy stopUpd:))
		)
	)
)

(instance tryAgain of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= local13 1)
				(HandsOff)
				(keith stopUpd:)
				(localproc_000c 101 56)
				(self cue:)
			)
			(2
				(ego
					setPri: 9
					illegalBits: 0
					setMotion: MoveTo 180 138 self
				)
			)
			(3 (self cue:))
			(4
				(willy setLoop: 1)
				(if (ego has: 30)
					(localproc_000c 101 57)
				else
					(localproc_000c 101 58 83)
				)
				(ego
					illegalBits: -16384
					setLoop: -1
					setCel: -1
					setPri: -1
				)
				(HandsOn)
				(= local10 0)
				(= local13 0)
				(= gotRadios 1)
				(= local15 20)
			)
			(5 (willy setLoop: 0))
		)
	)
)

(instance theDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(= local13 1)
				(if gotRadios
					(ego
						setPri: 9
						illegalBits: 0
						setMotion: MoveTo 109 136 self
					)
				else
					(ego setPri: 9 illegalBits: 0 setMotion: MoveTo 109 136)
					(keith
						setPri: 9
						setLoop: -1
						illegalBits: 0
						ignoreActors:
						setMotion: MoveTo 75 134 self
					)
				)
			)
			(2
				(ego
					setPri: -1
					setLoop: -1
					setCel: -1
					illegalBits: -16384
				)
				(= local13 0)
				(HandsOn)
				(if (not gotRadios)
					(keith
						illegalBits: -16384
						setMotion: Follow ego 20
						ignoreActors: 0
					)
				else
					(keith startUpd:)
				)
			)
		)
	)
)

(instance toTheParkScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 104 7)
				(= cycles 2)
			)
			(1
				(Display
					101
					59
					dsFONT
					0
					dsWIDTH
					290
					dsCOORD
					15
					43
					dsCOLOR
					15
				)
				(while
				(not (& ((= temp0 (Event new: 5)) type?) $0005))
					(temp0 dispose:)
				)
				(temp0 dispose:)
				(self cue:)
			)
			(2 (curRoom newRoom: 78))
		)
	)
)
