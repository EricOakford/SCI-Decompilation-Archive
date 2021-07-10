;;; Sierra Script 1.0 - (do not remove this comment)
(script# 363)
(include game.sh)
(use Main)
(use Intrface)
(use tunisia)
(use Sight)
(use System)

(public
	hasSugarScript 0
	hasFlourScript 1
	hasCoffeeScript 2
)
(synonyms
	(canister jar can)
)

(procedure (AlreadyDid)
	(Print 363 0)
)

(instance hasSugarScript of Script

	(method (doit)
		(super doit:)
		(if (CantBeSeen client ego 180) (client cue:))
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(Said 'open/canister,lid,top,sugar')
					(Said 'detach/lid,top[/canister]')
				)
				(if (not (& (tunisia sugarStat?) $0001))
					(Print 363 1)
					(tunisia sugarStat: (| (tunisia sugarStat?) $0001))
				else
					(AlreadyDid)
				)
			)
			(
				(or
					(Said 'replace/lid,top')
					(Said 'close/canister,lid,top,sugar')
				)
				(if (& (tunisia sugarStat?) $0001)
					(Print 363 2)
					(tunisia sugarStat: (& (tunisia sugarStat?) $fffe))
				else
					(AlreadyDid)
				)
			)
			((Said 'look<in/canister,sugar')
				(cond 
					((not (& (tunisia sugarStat?) $0001)) (Print 363 3))
					((ego has: 8) (Print 363 4))
					((& (tunisia sugarStat?) $0008) (Print 363 5))
					((& (tunisia sugarStat?) $0004) (Print 363 6))
					((& (tunisia sugarStat?) $0002) (Print 363 7))
					(else (Print 363 8))
				)
			)
			(
			(Said 'examine/canister,sugar,(canister<sugar,big)')
				(cond 
					((not (& (tunisia sugarStat?) $0001)) (Print 363 3))
					((ego has: 8) (Print 363 9))
					((& (tunisia sugarStat?) $0008) (Print 363 10))
					((& (tunisia sugarStat?) $0004) (Print 363 11))
					((& (tunisia sugarStat?) $0002) (Print 363 12))
					(else (Print 363 13) (Print 363 7))
				)
			)
			(
				(Said
					'empty,crap,pour[<out]/sugar,canister,(canister<sugar,big),content'
				)
				(cond 
					((not (& (tunisia sugarStat?) $0001)) (Print 363 3))
					((not (& (tunisia sugarStat?) $0002))
						(Print 363 14)
						(tunisia sugarStat: (| (tunisia sugarStat?) $0002))
					)
					(else (AlreadyDid))
				)
			)
			((Said 'open,detach/bottom[<false]')
				(cond 
					((not (& (tunisia sugarStat?) $0001)) (Print 363 3))
					((not (& (tunisia sugarStat?) $0002)) (Print 363 15))
					((not (& (tunisia sugarStat?) $0004))
						(Print 363 16)
						(tunisia sugarStat: (| (tunisia sugarStat?) $0004))
					)
					(else (AlreadyDid))
				)
			)
			(
			(Said 'detach,get,(look<below)/foam,rubber[<foam]')
				(cond 
					((not (& (tunisia sugarStat?) $0001)) (Print 363 17))
					((not (& (tunisia sugarStat?) $0002)) (Print 363 18))
					((not (& (tunisia sugarStat?) $0004)) (Print 363 19))
					((not (& (tunisia sugarStat?) $0008))
						(Print 363 20)
						(Print 363 21)
						(tunisia sugarStat: (| (tunisia sugarStat?) $0008))
					)
					(else (AlreadyDid))
				)
			)
			((Said 'get/gun')
				(cond 
					((not (& (tunisia sugarStat?) $0008)) (CantSee))
					((not (ego has: 8)) (Print 363 22) (ego get: 8) (theGame changeScore: 6))
					(else (AlreadyTook))
				)
			)
			(
			(Said 'replace/(bottom[<false]),foam,(rubber[<foam])') (DontNeedTo))
		)
	)
)

(instance hasFlourScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (CantBeSeen client ego 180) (client cue:))
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(Said 'open/canister,lid,top,flour')
					(Said 'detach/lid,top')
				)
				(if (not (& (tunisia flourStat?) $0001))
					(Print 363 23)
					(tunisia flourStat: (| (tunisia flourStat?) $0001))
				else
					(AlreadyDid)
				)
			)
			(
				(or
					(Said 'replace/lid,top')
					(Said 'close/canister,lid,top,flour')
				)
				(if (& (tunisia flourStat?) $0001)
					(Print 363 2)
					(tunisia flourStat: (& (tunisia flourStat?) $fffe))
				else
					(AlreadyDid)
				)
			)
			((Said 'look<in/canister,flour')
				(cond 
					((not (& (tunisia flourStat?) $0001)) (Print 363 3))
					((not (& (tunisia flourStat?) $0002)) (Print 363 24))
					(else (Print 363 4))
				)
			)
			((Said 'examine/canister,flour')
				(cond 
					((not (& (tunisia flourStat?) $0001)) (Print 363 3))
					((& (tunisia flourStat?) $0002) (Print 363 25))
					(else (Print 363 26))
				)
			)
			(
				(Said
					'empty,crap,pour[<out]/flour,canister,(canister<flour),(canister<medium),content'
				)
				(cond 
					((not (& (tunisia flourStat?) $0001)) (Print 363 3))
					((not (& (tunisia flourStat?) $0002))
						(Print 363 27)
						(tunisia flourStat: (| (tunisia flourStat?) $0002))
					)
					(else (AlreadyDid))
				)
			)
		)
	)
)

(instance hasCoffeeScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (CantBeSeen client ego 180) (client cue:))
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(Said 'open/canister,lid,top,coffee')
					(Said 'detach/lid,top')
				)
				(if (not (& (tunisia coffeeStat?) $0001))
					(Print 363 28)
					(tunisia coffeeStat: (| (tunisia coffeeStat?) $0001))
				else
					(AlreadyDid)
				)
			)
			(
				(or
					(Said 'replace/lid,top')
					(Said 'close/canister,lid,top,coffee')
				)
				(if (& (tunisia coffeeStat?) $0001)
					(Print 363 2)
					(tunisia coffeeStat: (& (tunisia coffeeStat?) $fffe))
				else
					(AlreadyDid)
				)
			)
			((Said 'look<in/canister,coffee')
				(cond 
					((not (& (tunisia coffeeStat?) $0001)) (Print 363 3))
					((not (& (tunisia coffeeStat?) $0002)) (Print 363 29))
					(else (Print 363 4))
				)
			)
			((Said 'examine/canister,coffee')
				(cond 
					((not (& (tunisia coffeeStat?) $0001)) (Print 363 3))
					((& (tunisia coffeeStat?) $0002) (Print 363 25))
					(else (Print 363 30))
				)
			)
			(
				(Said
					'empty,crap,pour[<out]/coffee,canister,(canister<coffee),(canister<little),content'
				)
				(cond 
					((not (& (tunisia coffeeStat?) $0001)) (Print 363 3))
					((not (& (tunisia coffeeStat?) $0002))
						(Print 363 31)
						(tunisia coffeeStat: (| (tunisia coffeeStat?) $0002))
					)
					(else (AlreadyDid))
				)
			)
		)
	)
)
