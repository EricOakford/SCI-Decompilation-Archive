;;; Sierra Script 1.0 - (do not remove this comment)
(script# 77)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room77 0
)

(local
	local0
	local1
	[jumpAreas 22]
	gEgoViewer
	ripple1
	ripple2
)
(instance Room77 of Room
	(properties
		picture 77
	)
	
	(method (init)
		(= east 78)
		(= west 73)
		(= horizon 40)
		(= isIndoors FALSE)
		(Load VIEW 21)
		(Load VIEW 69)
		(Load VIEW 49)
		(Load SCRIPT JUMP)
		(if isNightTime (= picture 177))
		(super init:)
		(self setRegions: SWAMP)
		(= ripple1 (Prop new:))
		(= ripple2 (Prop new:))
		(ripple1
			isExtra: TRUE
			view: 650
			loop: 2
			cel: 1
			posn: 241 145
			setPri: 0
			setCycle: Forward
			init:
		)
		(ripple2
			isExtra: TRUE
			view: 650
			loop: 4
			cel: 0
			posn: 275 112
			setPri: 0
			setCycle: Forward
			init:
		)
		(switch prevRoomNum
			(east
				(ego view: 2 posn: 301 161 loop: 1)
				(= local0 10)
				(= local1 20)
				(= currentStatus egoOnSwampGrass)
				(User canControl: TRUE canInput: TRUE)
			)
			(west
				(ego
					view: 49
					setLoop: 2
					cel: 0
					posn: 32 160
					setCycle: Forward
					setScript: egoCrawlOut
				)
			)
			(else 
				(ego
					view: 49
					setLoop: 2
					cel: 0
					posn: 32 160
					setCycle: Walk
					setScript: egoCrawlOut
				)
			)
		)
		(ego init:)
		(ego edgeHit: 0)
		(= [jumpAreas 0] 94)
		(= [jumpAreas 1] 167)
		(= [jumpAreas 2] 113)
		(= [jumpAreas 3] 164)
		(= [jumpAreas 4] 135)
		(= [jumpAreas 5] 162)
		(= [jumpAreas 6] 159)
		(= [jumpAreas 7] 165)
		(= [jumpAreas 8] 186)
		(= [jumpAreas 9] 161)
		(= [jumpAreas 10] 206)
		(= [jumpAreas 11] 165)
		(= [jumpAreas 12] 225)
		(= [jumpAreas 13] 161)
		(= [jumpAreas 14] 244)
		(= [jumpAreas 15] 166)
		(= [jumpAreas 16] 264)
		(= [jumpAreas 17] 163)
		(= [jumpAreas 18] 285)
		(= [jumpAreas 19] 167)
		(= [jumpAreas 20] 301)
		(= [jumpAreas 21] 162)
		(curRoom setScript: jump)
		(= inCutscene TRUE)
	)
	
	(method (doit)
		(super doit:)
		(if
			(or
				(== (ego view?) 5)
				(== (ego view?) 6)
				(== (ego view?) 7)
			)
			(= local0 0)
			(= local1 0)
		)
		(if
			(and
				(& (ego onControl: 0) $4000)
				(== (ego view?) 2)
				(== (ego script?) 0)
				(< (ego heading?) 285)
				(> (ego heading?) 255)
			)
			(ego setScript: egoCrawlIn)
		)
	)
	
	(method (dispose)
		(DisposeScript JUMP)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					(
						(or
							(Said 'look/room')
							(Said 'look/around')
							(Said 'look/marsh')
							(Said 'look[<around][/!*]')
						)
						(Print 77 0)
					)
					((Said 'look<in/cave') (Print 77 1))
					((Said 'look/cave') (Print 77 2))
					((Said 'crawl/')
						(if (ego inRect: 40 154 63 170)
							(ego setScript: egoCrawlIn)
						else
							(Print 77 3)
						)
					)
					((Said 'hop,hop') (jump changeState: 1))
				)
			else
				FALSE
			)
		)
	)
)

(instance egoCrawlOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= currentStatus egoNormal)
				(ego xStep: 1 setMotion: MoveTo 68 161 self)
			)
			(1
				(ego setLoop: 0 cel: 5 setCycle: BegLoop self)
			)
			(2
				(ego view: 21 setLoop: 0 cel: 6 setCycle: BegLoop self)
			)
			(3
				(ego view: 2 xStep: 3 yStep: 2 setLoop: -1 setCycle: Walk)
				(HandsOn)
				(ego setScript: 0)
				(= currentStatus egoOnSwampGrass)
			)
		)
	)
)

(instance egoCrawlIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setAvoider: Avoider)
				(ego setMotion: MoveTo 57 162 self)
			)
			(1
				(= currentStatus egoNormal)
				(ego view: 21 setLoop: 1 cel: 255 setCycle: EndLoop self)
			)
			(2
				(ego view: 49 setLoop: 1 cel: 255 setCycle: EndLoop self)
			)
			(3
				(ego
					setLoop: 3
					setCycle: Walk
					xStep: 1
					setMotion: MoveTo 32 161 self
				)
			)
			(4
				(HandsOn)
				(ego
					illegalBits: cWHITE
					setAvoider: 0
					xStep: 3
					yStep: 2
					setLoop: -1
					setScript: 0
				)
				(= inCutscene TRUE)
				(curRoom newRoom: 73)
			)
		)
	)
)

(instance jump of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(if (== currentStatus 13)
					(Print 77 4)
					(self changeState: 0)
				else
					(if (and (== local0 10) (== (ego loop?) 0))
						(HandsOn)
						(= inCutscene TRUE)
						(curRoom newRoom: 78)
						(return)
					)
					(if (== local0 0)
						(if
							(or
								(not (ego inRect: 87 161 106 172))
								(!= (ego loop?) 0)
								(!= (ego view?) 2)
							)
							(jump changeState: 10)
						else
							(= gEgoViewer (ego viewer?))
							(ego viewer: 0)
							(HandsOff)
							(ego view: 69 cel: 255 setCycle: EndLoop self)
						)
					else
						(= gEgoViewer (ego viewer?))
						(ego viewer: 0)
						(HandsOff)
						(ego view: 69 cel: 255 setCycle: EndLoop self)
					)
				)
			)
			(2 (ego setCycle: CycleTo 1 -1 self))
			(3
				(ego xStep: 6 yStep: 4)
				(cond 
					((== (ego loop?) 0)
						(ego setLoop: 2 cel: 255 setCycle: EndLoop)
						(++ local0)
						(= local1 (+ local1 2))
						(ego
							setMotion: JumpTo [jumpAreas local1] [jumpAreas (+ local1 1)] self
						)
					)
					((== (ego loop?) 1)
						(ego setLoop: 3 cel: 255 setCycle: EndLoop)
						(-- local0)
						(= local1 (- local1 2))
						(ego
							setMotion: JumpTo [jumpAreas local1] [jumpAreas (+ local1 1)] self
						)
					)
					((== (ego loop?) 2)
						(ego viewer: gEgoViewer)
						(ego setMotion: JumpTo (ego x?) (+ (ego y?) 6) self)
					)
					((== (ego loop?) 3)
						(ego viewer: gEgoViewer)
						(ego setMotion: JumpTo (ego x?) (- (ego y?) 6) self)
					)
				)
			)
			(4
				(if
					(and
						(!= (ego onControl: 1) 1024)
						(!= (ego onControl: 1) 1)
					)
					(ego
						setLoop: -1
						viewer: gEgoViewer
						setCycle: Walk
						setStep: 3 2
					)
					(HandsOn)
					(return)
				)
				(if (== (ego loop?) 2)
					(ego cel: -1 setLoop: -1 loop: 4 setCycle: EndLoop self)
				else
					(ego cel: -1 setLoop: -1 loop: 5 setCycle: EndLoop self)
				)
			)
			(5
				(if (== (ego loop?) 4)
					(ego view: 2 loop: 0 cel: 0 xStep: 3 yStep: 2)
				else
					(ego view: 2 loop: 1 cel: 0 xStep: 3 yStep: 2)
				)
				(HandsOn)
				(ego viewer: gEgoViewer)
				(ego view: 2 setCycle: Walk)
			)
			(10 (Print 77 5))
		)
	)
)
