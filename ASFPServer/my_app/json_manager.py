from django.http import JsonResponse


def get_json(data, code, message):
    return JsonResponse({"data": data, "code": code, "message": message}, safe=False)


def get_success_json(data):
    return JsonResponse({"data": data, "code": 200, "message": None}, safe=False)


def get_error_json(message):
    return JsonResponse({"data": None, "code": 201, "message": message}, safe=False)


def get_actor_list(data):
    actor_data = data.split('$')
    actor_list = []
    for item in actor_data:
        actor_info = item.split(',')
        data = dict()
        data['actor_img_url'] = actor_info[0]
        data['actor_name'] = actor_info[1]
        data['role_name'] = actor_info[2]
        actor_list.append(data)
    return actor_list
