;;; Sierra Script 1.0 - (do not remove this comment)
(script# 23)
(include sci.sh)
(use Main)
(use Intrface)
(use Reverse)
(use RFeature)
(use Path)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room23 0
)
(synonyms
	(lamp ignite)
)

(local
	local0
	local1
	local2
	local3
	local4
	[local5 7] = [222 130 186 137 193 142 -32768]
	[local12 5] = [222 149 193 142 -32768]
	[local17 7] = [222 130 186 137 210 147 -32768]
	[local24 5] = [222 149 201 147 -32768]
	local29
)
(instance Room23 of Rm
	(properties
		picture 23
	)
	
	(method (init)
		(= south 29)
		(= west 22)
		(= east 24)
		(= north 12)
		(= horizon 86)
		(super init:)
		(addToPics add: Well eachElementDo: #init doit:)
		(self setRegions: 206 setFeatures: Well House)
		(if (== currentAct 1) (self setRegions: 381))
		(Load rsSCRIPT 985)
		(Load rsPIC 70)
		(LoadMany 128 51 55 170)
		(LoadMany 132 82 120)
		(Thunder number: 17 loop: 0)
		(mySound number: 82 loop: 0)
		(Bucket init: setPri: 10 stopUpd:)
		(Windlass init: setPri: 10 stopUpd:)
		(WHandle init: setPri: 10 stopUpd:)
		(BigBucket ignoreActors: 1 init: hide:)
		(Splash cycleSpeed: 1 setPri: 10 init: stopUpd: hide:)
		(if howFast
			(Light1 setPri: 9 init: setScript: showers)
			(Light2 setPri: 9 init:)
		)
		(if
			(and
				(>= currentAct 3)
				(not (& global123 $0004))
				(!= gCurRoomNum 24)
				(!= gCurRoomNum curRoomNum)
			)
			(switch (Random 1 2)
				(1 (= gCurRoomNum curRoomNum))
				(2 (= gCurRoomNum 24))
			)
		)
		(if (and (== currentAct 3) (< global115 7))
			(self setRegions: 203)
			(= gCurRoomNum 24)
		)
		(switch prevRoomNum
			(29 (ego posn: 126 188))
			(18 (ego posn: 186 133))
			(22
				(if (> (ego y?) 150) (ego posn: 1 170))
			)
			(17 (ego posn: 70 135))
			(12 (ego posn: 70 135))
			(24
				(if (and (< (ego y?) 163) (> (ego y?) 156))
					(ego posn: 318 165)
				)
			)
		)
		(ego illegalBits: -32768 view: 0 init:)
	)
	
	(method (doit)
		(if (FirstEntry) (Print 23 0))
		(if (& (ego onControl: 0) $0002) (curRoom newRoom: 12))
		(if (& (ego onControl: 0) $0004) (curRoom newRoom: 18))
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 983)
		(DisposeScript 985)
		(DisposeScript 973)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said '/body,actress>')
						(cond 
							((!= gCurRoomNum curRoomNum) (event claimed: 1) (Print 23 1))
							((Said 'lift')
								(cond 
									(local1 (Print 23 2))
									((Windlass cycler?) (Print 23 3))
									(else (self setScript: raiseBucket))
								)
							)
							((Said 'lower,unwind') (if (not local1) (Print 23 4) else (= local0 1)))
							((Said 'kill') (Print 23 5))
							((Said 'kiss') (Print 23 6))
							((Said 'embrace') (Print 23 7))
							(
							(Said 'search,get,drag,drag,press,move,(examine<in)') (Print 23 8))
							((Said 'examine') (Print 23 9))
							((Said 'help') (Print 23 10))
						)
					)
					(
					(and (== gCurRoomNum curRoomNum) (Said 'examine/boa')) (Print 23 11))
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]') (Print 23 0))
							((Said '/path') (Print 23 12))
							((Said '<(in,down,in)/well')
								(if (and local1 (== gCurRoomNum curRoomNum))
									(Print 23 13)
								else
									(WHandle setScript: lookInWell)
								)
							)
							((Said '<in/doghouse') (NotClose))
							((Said '/hemp') (Print 23 14))
							((Said '/(door<basement),basement') (Print 23 15))
							((Said '/door,lamp') (Print 23 16))
							((Said '/monument') (Print 23 17))
							(
								(or
									(Said 'examine/doghouse')
									(Said 'examine/cabin<beauregard')
								)
								(Print 23 18)
							)
						)
					)
					((Said 'get/hemp') (Print 23 19))
					((Said 'get/control') (Print 23 20))
					((Said 'open/(door[<basement]),basement') (NotClose))
					((Said 'chop,untie/hemp,bucket') (Print 23 21))
					(
					(and (== local2 1) (Said 'drop/control,handle,bucket')) (= local2 0))
					((Said 'wind,rotate/control,handle,hemp')
						(cond 
							(local1 (= local0 1))
							((Windlass cycler?) (Print 23 3))
							(else (self setScript: raiseBucket))
						)
					)
					((Said 'enter,climb/well,bucket') (Print 23 22))
					(
						(and
							local1
							local2
							(Said 'let,release/go,handle,control')
						)
						(ego x: (- (ego x?)) heading: 180)
					)
				)
			else
				0
			)
		)
	)
	
	(method (newRoom n)
		(if local4
			(= global123 (| global123 $0004))
			(= gCurRoomNum 0)
		)
		(super newRoom: n)
	)
)

(instance raiseBucket of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setAvoider: (Avoid new:))
				(cond 
					((ego inRect: 222 115 320 139) (ego setMotion: TopPath self))
					((ego inRect: 222 139 320 158) (ego setMotion: BotPath self))
					(else (ego setMotion: MoveTo 193 142 self))
				)
				(= local2 1)
			)
			(1
				(wellCage init:)
				(ego setAvoider: 0 observeBlocks: wellCage hide:)
				(myMusic loop: -1 play:)
				(WHandle
					view: 51
					loop: 2
					cel: 0
					posn: 193 142
					cycleSpeed: 1
				)
				(Windlass cycleSpeed: 1 setCycle: Fwd)
				(= local3 0)
				(= cycles 1)
			)
			(2 (WHandle setCycle: End self))
			(3
				(if (!= (++ local3) 5) (= state 1))
				(= cycles 1)
			)
			(4
				(Bucket
					loop: (if (== gCurRoomNum curRoomNum) 5 else 4)
					cel: 0
					cycleSpeed: 4
					setCycle: End
				)
				(WHandle setCycle: End self)
			)
			(5
				(myMusic stop:)
				(if (not local1) (= local1 1) (HandsOn))
				(if
					(and
						(== gCurRoomNum curRoomNum)
						(not (& global123 $0040))
						(not local4)
					)
					(mySound number: 120 loop: 1 play:)
					(= local4 1)
					(Print 23 23 #at 10 20 #icon 363 2 0 #mode 1)
				)
				(Windlass setCycle: 0)
				(cond 
					(local0
						(HandsOff)
						(= local1 (= local0 0))
						(WHandle setScript: lowerBucket)
						(= state 9)
						(= cycles 1)
					)
					(
					(and local2 (== (ego x?) 193) (== (ego y?) 142)) (= state 4) (= cycles 1))
					(else
						(= local1 0)
						(ego
							loop: 2
							posn: 193 142
							ignoreBlocks: wellCage
							show:
							setCycle: Walk
						)
						(myMusic loop: -1 play:)
						(if local2 (DirLoop ego (ego heading?)))
						(WHandle
							view: 123
							loop: 6
							cel: 0
							posn: 207 109
							cycleSpeed: 0
							setCycle: End self
						)
						(Windlass cycleSpeed: 0 setCycle: Fwd)
						(Bucket cycleSpeed: 0 setCycle: Beg)
						(= local3 0)
					)
				)
			)
			(6
				(Bucket loop: 7 cel: 1 stopUpd:)
				(WHandle cel: 0 setCycle: End self)
			)
			(7
				(if (!= (++ local3) 5) (= state 5))
				(= cycles 1)
			)
			(8
				(Windlass setCycle: 0)
				(WHandle cel: 0 stopUpd:)
				(myMusic stop:)
				(if (== gCurRoomNum curRoomNum)
					(= seconds 2)
				else
					(= state 9)
					(mySound number: 82 play:)
					(= cycles 1)
				)
			)
			(9
				(mySound number: 82 play:)
				(Splash setCycle: End self show:)
			)
			(10
				(Splash cel: 0 hide:)
				(= local2 0)
				(client setScript: 0)
			)
		)
	)
)

(instance lowerBucket of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Windlass cycleSpeed: 1 setCycle: Fwd)
				(Bucket setCycle: Beg self)
				(WHandle setCycle: Rev)
				(myMusic loop: -1 play:)
				(= local3 0)
			)
			(1
				(Bucket loop: 7 cel: 1 stopUpd:)
				(= cycles 1)
			)
			(2 (WHandle setCycle: Beg self))
			(3
				(if (!= (++ local3) 5) (= state 1))
				(= cycles 1)
			)
			(4
				(myMusic stop:)
				(Windlass setCycle: 0)
				(ego
					loop: 2
					posn: 193 142
					ignoreBlocks: wellCage
					show:
					setCycle: Walk
				)
				(WHandle view: 123 loop: 6 cel: 0 posn: 207 109 stopUpd:)
				(HandsOn)
				(= local2 0)
				(client setScript: 0)
			)
		)
	)
)

(instance lookInWell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					(local1 (= cycles 1))
					((ego inRect: 222 115 320 139) (ego setAvoider: (Avoid new:) setMotion: TopLPath self))
					((ego inRect: 222 139 320 158) (ego setAvoider: (Avoid new:) setMotion: BotLPath self))
					(else
						(ego
							setAvoider: (Avoid new:)
							setMotion: MoveTo 211 147 self
						)
					)
				)
			)
			(1
				(if local1
					(= cycles 1)
				else
					(ego view: 55 loop: 0 cel: 0 setCycle: End self)
				)
			)
			(2
				(cast eachElementDo: #hide)
				(if local1 (BigBucket show:))
				(DrawPic 70 dpOPEN_CENTEREDGE)
				(if local29
					(if local1
						(Print 23 24 #at 160 132 #width 140)
					else
						(DontSee)
					)
				else
					(Print 23 25 #at 160 132 #width 140)
				)
				(= cycles 1)
			)
			(3
				(= local29 0)
				(cls)
				(DrawPic curRoomNum dpOPEN_EDGECENTER)
				(addToPics doit:)
				(cast eachElementDo: #show)
				(Splash hide:)
				(BigBucket hide:)
				(HandsOn)
				(if local1
					(ego posn: 193 142 observeBlocks: wellCage hide:)
					(= cycles 1)
				else
					(ego setAvoider: 0 setCycle: Beg self)
				)
			)
			(4
				(if (not local1) (ego view: 0 setCycle: Walk))
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance showers of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (= state 3)))
			(1
				(Light1 setCycle: Fwd)
				(Light2 setCycle: Fwd)
				(= cycles 7)
			)
			(2
				(Light1 setCycle: End)
				(Light2 setCycle: End self)
			)
			(3 (Thunder loop: 1 play: self))
			(4
				(if (< (Random 1 100) 25) (= state 0))
				(= cycles 7)
			)
			(5 (= state 3) (= seconds 5))
		)
	)
)

(instance Well of RPicView
	(properties
		y 144
		x 223
		view 123
		loop 2
		signal $4000
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine[<at]/well'))
			(event claimed: 1)
			(Print 23 26)
		)
	)
)

(instance BigBucket of Prop
	(properties
		y 114
		x 154
		view 170
	)
)

(instance WHandle of Prop
	(properties
		y 109
		x 207
		view 123
		loop 6
	)
)

(instance Windlass of Prop
	(properties
		y 113
		x 218
		view 123
		loop 3
	)
)

(instance Bucket of Prop
	(properties
		y 113
		x 223
		view 123
		loop 7
		cel 1
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine/control')
				(if (ego has: 20)
					(event claimed: 0)
				else
					(Print 23 27)
				)
			)
			(
			(Said 'enter,(go,hop,climb,get,sit)<in/bucket,well') (Print 23 28))
			((Said 'examine<in/bucket')
				(if (and local1 (== gCurRoomNum curRoomNum))
					(Print 23 13)
				else
					(= local29 1)
					(Bucket setScript: lookInWell)
				)
			)
			((Said 'lift/bucket,hemp')
				(cond 
					(local1 (Print 23 2))
					((Windlass cycler?) (Print 23 3))
					(else (Room23 setScript: raiseBucket))
				)
			)
			((Said 'lower,unwind/bucket,hemp') (if (not local1) (Print 23 4) else (= local0 1)))
			((Said 'get/bucket') (Print 23 29))
			(
			(or (MousedOn self event 3) (Said 'examine/bucket'))
				(event claimed: 1)
				(cond 
					((not local1) (DontSee))
					((== gCurRoomNum curRoomNum) (Print 23 30))
					(else (Print 23 24))
				)
			)
		)
	)
)

(instance Splash of Prop
	(properties
		y 124
		x 223
		view 123
		loop 8
	)
)

(instance Light1 of Prop
	(properties
		y 13
		x 261
		view 123
		cel 1
	)
)

(instance Light2 of Prop
	(properties
		y 18
		x 251
		view 123
		loop 1
		cel 1
	)
)

(instance House of RFeature
	(properties
		nsTop 6
		nsBottom 98
		nsRight 14
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {house})
		)
	)
)

(instance Thunder of Sound
	(properties)
)

(instance mySound of Sound
	(properties)
)

(instance myMusic of Sound
	(properties
		number 37
		priority 5
	)
)

(instance TopPath of Path
	(properties)
	
	(method (at param1)
		(return [local5 param1])
	)
)

(instance BotPath of Path
	(properties)
	
	(method (at param1)
		(return [local12 param1])
	)
)

(instance TopLPath of Path
	(properties)
	
	(method (at param1)
		(return [local17 param1])
	)
)

(instance BotLPath of Path
	(properties)
	
	(method (at param1)
		(return [local24 param1])
	)
)

(instance wellCage of Cage
	(properties
		top 125
		left 150
		bottom 160
		right 235
	)
)
